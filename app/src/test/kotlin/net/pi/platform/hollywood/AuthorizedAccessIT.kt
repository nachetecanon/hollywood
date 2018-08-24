package net.pi.platform.hollywood

import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.repository.DashboardRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockserver.client.MockServerClient
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI
import javax.annotation.PostConstruct


@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(Application::class))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("authx-integration-test")
class AuthorizedAccessIT {

    companion object {
        init {
            System.setProperty("spring.cloud.config.enabled", "false")
        }
    }

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var dashboardRepository: DashboardRepository

    @Autowired
    lateinit var clientHttpRequestInterceptor: ClientHttpRequestInterceptor

    val localhost_uri = "http://localhost:"

    @Value("\${local.server.port}")
    private val port: Int? = null

    @PostConstruct
    fun setUp() = restTemplate.restTemplate.interceptors.add(clientHttpRequestInterceptor)

    @Before
    fun cleanUp() {
        dashboardRepository.deleteAll()
    }

    @Test
    fun `verify return all dashboards because it is an authorized request`() {
        // Given
        MockServerClient("127.0.0.1", 1080)
                .reset()
                .`when`(
                        request()
                                .withMethod("GET")
                                .withPath("/auth/hollywood/dashboards")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader(Header.header("Content-Type", "application/json;charset=UTF-8"))
                                .withBody(
                                        """
                                            [{"path":"/hollywood/dashboards/28",
                                            "groups": [{"group":{"path":"/company"},"permissions":["read"]}]}]
                                        """
                                )
                );
        dashboardRepository.save(DataSamplesObjects.getDashboardWithoutId())
        val endpoint = URI.create(localhost_uri + port + "/api/dashboards")
        val request = RequestEntity<Any>(HttpMethod.GET, endpoint)

        // When
        val dashboardResponseEntity = restTemplate.exchange(request, typeRef<List<Dashboard>>())

        // Then
        assertEquals(HttpStatus.OK, dashboardResponseEntity.getStatusCode())
    }

    @Test
    fun `verify return forbidden error because it is an unauthorized request`() {
        // Given
        dashboardRepository.save(DataSamplesObjects.getDashboardWithoutId())
        val endpoint = URI.create(localhost_uri + port + "/api/dashboards/28")
        val request = RequestEntity<Any>(HttpMethod.GET, endpoint)

        // When
        val response = restTemplate.exchange(request, typeRef<Void>())
        // Then
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.statusCode)

    }

    @Test
    fun `verify return filtered dashboards because it is filtering the list of results`() {
        // Given
        MockServerClient("127.0.0.1", 1080)
                .reset()
                .`when`(
                        request()
                                .withMethod("GET")
                                .withPath("/auth/hollywood/dashboards")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader(Header.header("Content-Type", "application/json;charset=UTF-8"))
                                .withBody(
                                        """
                                            [{"path":"/hollywood/dashboards/28",
                                            "groups": [{"group":{"path":"/company"},"permissions":["read"]}]}]
                                        """
                                )
                );
        val dashboardAbc = DataSamplesObjects.getDashboard("abc")
        val dashboardDef = DataSamplesObjects.getDashboard("def")
        val dashboardGhi = DataSamplesObjects.getDashboard("ghi")
        val dashboard28 = DataSamplesObjects.getDashboard("28")
        dashboardRepository.saveAll(listOf(dashboardAbc, dashboardDef, dashboardGhi, dashboard28))
        val endpoint = URI.create(localhost_uri + port + "/api/dashboards")
        val request = RequestEntity<Any>(HttpMethod.GET, endpoint)
        // When
        val dashboardResponseEntity = restTemplate.exchange(request, typeRef<List<Dashboard>>())

        // Then
        assertEquals(HttpStatus.OK, dashboardResponseEntity.getStatusCode())
        assertEquals(1, dashboardResponseEntity.body?.size)
        assertEquals("28", dashboardResponseEntity.body?.get(0)?.id)
    }

    private inline fun <reified T : Any> typeRef(): ParameterizedTypeReference<T> = object : ParameterizedTypeReference<T>() {}

}



