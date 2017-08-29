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
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(HollywoodServiceApplication::class))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
class SaveDashboardIT() {

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
    }

    @Test
    fun `when save dashboard return dashboard`() {
        val dashboard = DataSamplesObjects.getDashboard();
        val dashboardResponseEntity = testRestemplate.postForEntity(localhost_uri + port + "/api/dashboards", dashboard, Dashboard::class.java)
        assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.OK)
        val resultDashboard = dashboardResponseEntity.getBody()
        assertEquals(dashboardRepository.findOne(resultDashboard.id), resultDashboard)
    }
}


