package net.pi.platform.hollywood.model.service;

import net.pi.platform.hollywood.model.Dashboard;
import net.pi.platform.hollywood.model.repository.DashboardRepository;

import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

  private final DashboardRepository dashboardRepository;

  public DashboardServiceImpl(DashboardRepository dashboardRepository) {
    this.dashboardRepository = dashboardRepository;
  }

  
  @Override
  public Dashboard saveDashboard(Dashboard dashboard) {

    return dashboardRepository.save(dashboard);
  }
}
