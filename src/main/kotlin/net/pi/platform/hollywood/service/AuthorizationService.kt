package net.pi.platform.hollywood.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
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


    fun fetchAuthorizedResources(token: String, application: String, entity: String): List<AuthorizedResource> {
        val headers = HttpHeaders()
        headers.put(AUTHORIZATION, arrayListOf("Bearer ${token}"))
        val url: String = UriComponentsBuilder.fromHttpUrl(authxUrl)
                .pathSegment("applications", application, "entities", entity)
                .build().toString()
        val response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity(null, headers), Object::class.java)
        when {
            response.statusCode.is2xxSuccessful -> return response.body as List<AuthorizedResource>
            else -> {
                //In case of 4XX status useful error message could reside in WWW-Authenticate header
                val message = response.headers["WWW-Authenticate"] ?: response.body
                throw RuntimeException("Cannot access authorization module ${response.statusCode} from $url : $message")
            }
        }
    }

}

data class AuthorizedResource(val resource: String, val operations: List<String>)