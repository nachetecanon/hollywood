package net.pi.platform.hollywood

import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.repository.DashboardRepository
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.util.UriComponentsBuilder
import javax.annotation.PostConstruct


@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(Application::class))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
class SaveDashboardIT {

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
    @Qualifier("addKeycloakHeaderHttpRequestInterceptor")
    lateinit var clientHttpRequestInterceptor: ClientHttpRequestInterceptor

    @Value("\${local.server.port}")
    private val port: Int? = null

    private var id: String? = null

    @PostConstruct
    fun setUp() = restTemplate.restTemplate.interceptors.add(clientHttpRequestInterceptor)

    @After
    fun tearDown() {
        dashboardRepository.deleteAll()
    }

    @Test
    fun `test create when save dashboard then return dashboard`() {
        val dashboard = DataSamplesObjects.getDashboard()
        val dashboardResponseEntity = restTemplate.postForEntity(getRequestPathFor("dashboards"), dashboard, Dashboard::class.java)
        assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.OK)
        val resultDashboard = dashboardResponseEntity.getBody()
        assertEquals(dashboardRepository.findOne(resultDashboard.id), resultDashboard)
    }

    @Test
    fun `test create when save dashboard with visualization return dashboard with that visualization`() {
        val dashboard = DataSamplesObjects.getDashboardWithVisualization()
        val dashboardResponseEntity = restTemplate.postForEntity(getRequestPathFor("dashboards"), dashboard, Dashboard::class.java)
        assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.OK)
        val resultDashboard = dashboardResponseEntity.getBody()
        assertEquals(dashboardRepository.findOne(resultDashboard.id), resultDashboard)
    }

    @Test
    fun `test create when return dashboard with unknown visualization object return dashboard`() {
        val dashboard = DataSamplesObjects.getDashboardWithUknownVisualizationJsonString()
        val dashboardResponseEntity = restTemplate.postForEntity(getRequestPathFor("dashboards"), dashboard, Dashboard::class.java)
        assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.OK)
        val resultDashboard = dashboardResponseEntity.getBody()
        assertEquals(dashboardRepository.findOne(resultDashboard.id), resultDashboard)
    }

    @Test
    fun `test create when save dashboard and with id then throws WrongInputValue exception`() {
        val dashboard = DataSamplesObjects.getDashboard().copy(id = "blabla")
        val dashboardResponseEntity = restTemplate.postForEntity(getRequestPathFor("dashboards"), dashboard, Map::class.java)
        val requestBody = dashboardResponseEntity.body
        assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.BAD_REQUEST)
        assertEquals(requestBody.get("errorMessage"), "You cannot create a dashboard with id")
        assertEquals(requestBody.get("errorCode"), "invalid.input")

    }

    @Test
    fun `test update when exists dashboard then update dashboard and return it`() {
        val dashboard = DataSamplesObjects.getDashboard()
        val dashboardResponseEntity = restTemplate.postForEntity(getRequestPathFor("dashboards"), dashboard, Dashboard::class.java)

        val entity = HttpEntity(DataSamplesObjects.getDashboard(), HttpHeaders())
        val response = restTemplate.exchange(getRequestPathFor("dashboards", dashboardResponseEntity.body.id), HttpMethod.PUT, entity, Void::class.java)
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT)
    }

    @Test
    fun `test update when not exists dashboard then throws EntityNotFound exception`() {
        val entity = HttpEntity(DataSamplesObjects.getDashboard(), HttpHeaders())
        val response = restTemplate.exchange(getRequestPathFor("dashboards", "not_found"), HttpMethod.PUT, entity, Map::class.java)
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


