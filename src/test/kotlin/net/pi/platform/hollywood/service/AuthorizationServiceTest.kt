package net.pi.platform.hollywood.service

import com.fasterxml.jackson.databind.ObjectMapper
import junit.framework.TestCase.fail
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockserver.client.MockServerClient
import org.mockserver.junit.MockServerRule
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response


class AuthorizationServiceTest {

    @get:Rule
    val rule: MockServerRule = MockServerRule(this)
    private lateinit var mockServerClient: MockServerClient

    val objectMapper = ObjectMapper()

    @Test
    fun `should return authorization data in right format`() {
        //Given
        val host = "http://localhost:${rule.getPort()}"

        val authorizationService = AuthorizationService(host, true)
        val getResults = request("/hollywood/dashboards")
        val groupPermission = mapOf("group" to mapOf("path" to "/company/uk"),
                "permissions" to listOf("read"))
        val dashboard = mapOf("type" to "item", "path" to "/hollywood/dashboards/123",
                "groups" to listOf(groupPermission))
        val queryResults = response(objectMapper.writeValueAsString(listOf(dashboard)))
                .withHeader(Header.header("content-type", "application/json;charset=UTF-8"))
                .withStatusCode(200)
        mockServerClient.`when`(getResults).respond(queryResults)

        //When
        val result = authorizationService.fetchAuthorizedResources("werw", "hollywood", "dashboards")

        //Then
        assertThat(listOf(AuthorizedEntity("123", listOf("read")))).isEqualTo(result)

    }

    @Test()
    fun `should failed if authorization is not in right format`() {
        //Given
        val host = "http://localhost:${rule.getPort()}"

        val authorizationService = AuthorizationService(host, true)
        val getResults = request("/hollywood/dashboards")
        val groupPermission = mapOf("group" to mapOf("path" to "/company/uk"),
                "wrongkey" to listOf("read"))
        val dashboard = mapOf("type" to "item", "path" to "/hollywood/dashboards/123",
                "groups" to listOf(groupPermission))
        val queryResults = response(objectMapper.writeValueAsString(listOf(dashboard)))
                .withHeader(Header.header("content-type", "application/json;charset=UTF-8"))
                .withStatusCode(200)
        mockServerClient.`when`(getResults).respond(queryResults)

        //When
        try {
            authorizationService.fetchAuthorizedResources("werw", "hollywood", "dashboards")
            fail("should fail as receiving invalid from authX")
            //Then
        } catch (e: RuntimeException) {
            assertThat(e.message).containsIgnoringCase("Mismatch payload")
        }


    }

}