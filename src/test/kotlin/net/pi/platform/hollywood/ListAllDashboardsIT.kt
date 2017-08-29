package net.pi.platform.hollywood

import junit.framework.Assert.assertEquals
import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.repository.DashboardRepository
import org.junit.Before
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
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI


@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(HollywoodServiceApplication::class))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
class ListAllDashboardsIT() {

    @Autowired
    lateinit var testRestemplate: TestRestTemplate;

    @Autowired
    lateinit var dashboardRepository: DashboardRepository

    val localhost_uri = "http://localhost:";

    @Value("\${local.server.port}")
    private val port: Int? = null;

    @Before
    fun setUp() {
        dashboardRepository.deleteAll();

        dashboardRepository.save(listOf(DataSamplesObjects.getDashboard()))

    }

    @Test
    fun `when save dashboard return dashboard`() {

        val endpoint = URI.create(localhost_uri + port + "/api/dashboards")
        val request = RequestEntity<Any>(HttpMethod.GET, endpoint)
        val dashboardResponseEntity = testRestemplate.exchange(request, typeRef<List<Dashboard>>())

        assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.OK)

        val resultDashboards = dashboardResponseEntity.getBody();
        val testResults: List<Dashboard> = dashboardRepository.findAll();
        assertEquals(resultDashboards, testResults)


    }
}

inline fun <reified T : Any> typeRef(): ParameterizedTypeReference<T> = object : ParameterizedTypeReference<T>() {}

