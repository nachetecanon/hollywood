package net.pi.platform.hollywood

import junit.framework.Assert.assertEquals
import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.repository.DashboardRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.util.UriComponentsBuilder


@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(HollywoodServiceApplication::class))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
class SaveDashboardIT() {

    companion object {
        init {
            System.setProperty("spring.cloud.config.enabled", "false")
        }
    }

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate;

    @Autowired
    lateinit var dashboardRepository: DashboardRepository

    @Value("\${local.server.port}")
    private val port: Int? = null;

    private var id: String? = null

    @Before
    fun setUp() {
        id = dashboardRepository.save(DataSamplesObjects.getDashboard()).id
    }

    @After
    fun tearDown() {
        dashboardRepository.deleteAll()
    }

    @Test
    fun `test create when save dashboard then return dashboard`() {
        val dashboard = DataSamplesObjects.getDashboard();
        val dashboardResponseEntity = testRestTemplate.postForEntity(getRequestPathFor("dashboards"), dashboard, Dashboard::class.java)
        assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.OK)
        val resultDashboard = dashboardResponseEntity.getBody()
        assertEquals(dashboardRepository.findOne(resultDashboard.id), resultDashboard)
    }

    @Test
    fun `test create when save dashboard with visualization return dashboard with that visualization`() {
        val dashboard = DataSamplesObjects.getDashboardWithVisualization();
        val dashboardResponseEntity = testRestTemplate.postForEntity(getRequestPathFor("dashboards"), dashboard, Dashboard::class.java)
        assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.OK)
        val resultDashboard = dashboardResponseEntity.getBody()
        assertEquals(dashboardRepository.findOne(resultDashboard.id), resultDashboard)
    }

    @Test
    fun `test create when return dashboard with unknown visualization object return dashboard`() {
        val dashboard = DataSamplesObjects.getDashboardWithUknownVisualizationJsonString();
        val dashboardResponseEntity = testRestTemplate.postForEntity(getRequestPathFor("dashboards"), dashboard, Dashboard::class.java)
        assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.OK)
        val resultDashboard = dashboardResponseEntity.getBody()
        assertEquals(dashboardRepository.findOne(resultDashboard.id), resultDashboard)
    }

    @Test
    fun `test create when save dashboard and with id then throws WrongInputValue exception`() {
        val dashboard = DataSamplesObjects.getDashboard().copy(id = "blabla");
        val dashboardResponseEntity = testRestTemplate.postForEntity(getRequestPathFor("dashboards"), dashboard, Map::class.java)
        val requestBody = dashboardResponseEntity.body
        assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.BAD_REQUEST)
        assertEquals(requestBody.get("errorMessage"), "You cannot create a dashboard with id")
        assertEquals(requestBody.get("errorCode"), "invalid.input")

    }

    @Test
    fun `test update when exists dashboard then update dashboard and return it`() {
        val entity = HttpEntity(DataSamplesObjects.getDashboard(), HttpHeaders())
        val response = testRestTemplate.exchange(getRequestPathFor("dashboards", id), HttpMethod.PUT, entity, Void::class.java)
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT)
    }

    @Test
    fun `test update when not exists dashboard then throws EntityNotFound exception`() {
        val entity = HttpEntity(DataSamplesObjects.getDashboard(), HttpHeaders())
        val response = testRestTemplate.exchange(getRequestPathFor("dashboards", "not_found"), HttpMethod.PUT, entity, Map::class.java)
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND)
        val result = response.body
        assertEquals(result.get("errorMessage"), "Unable to update dashboard: Dashboard with id[not_found] not found")
        assertEquals(result.get("errorCode"), "uri.not.found")
    }

    private fun getRequestPathFor(vararg path: String?): String {
        return UriComponentsBuilder.fromHttpUrl(String.format("http://localhost:%d/api", port))
                .pathSegment(*path).build().toString()
    }
}


