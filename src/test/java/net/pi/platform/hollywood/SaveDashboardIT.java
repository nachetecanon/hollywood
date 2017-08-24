package net.pi.platform.hollywood;

import net.pi.platform.hollywood.model.Dashboard;
import net.pi.platform.hollywood.model.repository.DashboardRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
public class SaveDashboardIT {

  private static final String LOCALHOST_URI = "http://localhost:";

  @Value("${local.server.port}")
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private DashboardRepository dashboardRepository;
  private Dashboard dashboard;


  @Before
  public void SetUp() {
    dashboardRepository.deleteAll();
    this.dashboard = DashboardEntitiesClasses.getDashboard();
  }


  @Test
  public void testSaveOneDashBoard() {

    ResponseEntity<Dashboard> dashboardResponseEntity =
      restTemplate.postForEntity(LOCALHOST_URI + port + "/api/dashboards", dashboard, Dashboard
        .class);
    assertEquals(dashboardResponseEntity.getStatusCode(), HttpStatus.OK);
    Dashboard resultDashboard = dashboardResponseEntity.getBody();
    assertEquals(dashboardRepository.findOne(resultDashboard.getId()), resultDashboard);
  }
}
