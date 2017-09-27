package net.pi.platform.hollywood

import org.junit.Assert.assertEquals
import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.repository.DashboardRepository
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
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
@ActiveProfiles("integration-test")
class ListAllDashboardsIT {

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
        dashboardRepository.save(listOf(DataSamplesObjects.getDashboard()))
    }

    @Test
    @Ignore
    fun `when save dashboard return dashboard`() {

        val endpoint = URI.create(localhost_uri + port + "/api/dashboards")
        val request = RequestEntity<Any>(HttpMethod.GET, endpoint)
        val dashboardResponseEntity = restTemplate.exchange(request, typeRef<List<Dashboard>>())

        assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.OK)

        val resultDashboards = dashboardResponseEntity.getBody()
        val testResults: List<Dashboard> = dashboardRepository.findAll()
        assertEquals(resultDashboards, testResults)
    }
}

inline fun <reified T : Any> typeRef(): ParameterizedTypeReference<T> = object : ParameterizedTypeReference<T>() {}

