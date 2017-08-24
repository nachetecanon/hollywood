package net.pi.platform.hollywood.model.service;

import net.pi.platform.hollywood.DashboardEntitiesClasses;
import net.pi.platform.hollywood.model.Dashboard;
import net.pi.platform.hollywood.model.repository.DashboardRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(JUnit4.class)
public class DashboardServiceImplTest {


  DashboardServiceImpl dashboardService;

  private final DashboardRepository dashboardRepository = mock(DashboardRepository.class);

  public DashboardServiceImplTest() {
    dashboardService = new DashboardServiceImpl(dashboardRepository);
  }

  @Test
  public void testSave() {
    Dashboard dashboard = DashboardEntitiesClasses.getDashboard();
    given(dashboardRepository.save(dashboard)).willReturn(dashboard);
    Dashboard dashboardReturned = dashboardService.saveDashboard(dashboard);
    assertEquals(dashboardReturned, dashboard);
    verify(this.dashboardRepository, times(1)).save(dashboard);
    verifyNoMoreInteractions(this.dashboardRepository);
  }

}
