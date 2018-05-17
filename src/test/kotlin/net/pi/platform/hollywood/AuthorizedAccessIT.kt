package net.pi.platform.hollywood

import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.repository.DashboardRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI
import javax.annotation.PostConstruct


@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(Application::class))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("authx-integration-test")
@AutoConfigureStubRunner(
    repositoryRoot = "http://nexus3.pibenchmark.com/nexus/repository/maven-releases",
    ids = arrayOf("net.pi.platform.authx:authx-service:+:stubs:8383"),
    workOffline = false)
@DirtiesContext
class AuthorizedAccessIT {

    companion object {
        init {
            System.setProperty("spring.cloud.config.enabled", "false")
            System.setProperty("stubrunner.snapshot-check-skip", "true")
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
        dashboardRepository.save(listOf(DataSamplesObjects.getDashboard()))
    }

    @Test
    fun `verify return all dashboards because it is an authorized request`() {
        // Given
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
        val endpoint = URI.create(localhost_uri + port + "/api/dashboards/28")
        val request = RequestEntity<Any>(HttpMethod.GET, endpoint)

        // When
        val response = restTemplate.exchange(request, typeRef<Void>())
        // Then
        assertEquals(HttpStatus.FORBIDDEN, response.statusCode)

    }

}



