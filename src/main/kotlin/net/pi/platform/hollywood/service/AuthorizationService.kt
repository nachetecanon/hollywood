package net.pi.platform.hollywood.service

import net.pi.platform.common.api.exception.ForbiddenException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.stereotype.Service
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class AuthorizationService(@Value("\${authx.url:NA}") val authxUrl: String,
                           @Value("\${authx.enabled:false}") val authEnabled: Boolean) {

    private val logger = LoggerFactory.getLogger(AuthorizationService::class.java)

    private val restTemplate: RestTemplate = RestTemplate()

    init {
        //Skip default error handling
        this.restTemplate.errorHandler = object : DefaultResponseErrorHandler() {
            override fun hasError(statusCode: HttpStatus): Boolean {
                return false
            }
        }
        //Check configuration
        if (authEnabled) {
            logger.info("Authorization enabled")
            if (authxUrl.equals("NA")) {
                logger.warn("authx.url is missing in the configuration")
            }
        } else {
            logger.info("Authorization not enabled")
        }
    }


    fun fetchAuthorizedResources(token: String, application: String, collection: String): List<AuthorizedEntity> {
        val headers = HttpHeaders()
        headers.put(AUTHORIZATION, arrayListOf("Bearer ${token}"))
        //added to match authX contract
        headers.contentType = APPLICATION_JSON_UTF8
        val path = "/${application}/${collection}/"
        val url: String = UriComponentsBuilder.fromHttpUrl(authxUrl)
                .pathSegment(application, collection)
                .build().toString()
        val response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity(null, headers), Any::class.java)
        when {
            response.statusCode.is2xxSuccessful -> {
                //Need to convert explicitly result when response is OK
                val result = response.body as List<Map<String, Any>>
                return result.map { convertAuthorizedEntity(path, it) }
            }
            response.statusCode.is4xxClientError -> {
                val message = when {
                    response.headers.containsKey("WWW-Authenticate") -> response.headers["WWW-Authenticate"]
                    else -> {
                        val payload = response.body as Map<String, Any>
                        if (payload.containsKey("errorMessage")) payload["errorMessage"].toString() else response.body.toString()
                    }
                }
                logger.warn("UnauthorizedException of ${application} ${collection} for user ${token}")
                throw ForbiddenException("${message}")
            }
            else -> {
                val message = response.body
                throw RuntimeException("Cannot access authorization module ${response.statusCode} from $url : $message")
            }
        }
    }

    private fun convertAuthorizedEntity(path: String, payload: Map<String, Any>): AuthorizedEntity {
        val resource = payload["path"] as String
        val id = if (resource.startsWith(path)) {
            resource.substring(path.length)
        } else {
            throw RuntimeException("Cannot extract resource Id from ${resource}")
        }
        val groups = payload["groups"]
        if (groups is List<*>) {
            val operations = groups.flatMap {
                if (it !is Map<*, *> || it["permissions"] !is List<*>) {
                    throw RuntimeException("Mismatch payload ${groups}")
                } else {
                    it["permissions"] as List<String>
                }
            }
            return AuthorizedEntity(id, operations)
        } else {
            throw RuntimeException("Mismatch payload groups=${groups}")
        }
    }
}

data class AuthorizedEntity(val id: String, val operations: List<String>)