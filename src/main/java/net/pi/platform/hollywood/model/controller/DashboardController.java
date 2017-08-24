package net.pi.platform.hollywood.model.controller;

import net.pi.platform.hollywood.model.Dashboard;
import net.pi.platform.hollywood.model.service.DashboardService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("dashboards")
public class DashboardController {

  public final DashboardService dashboardService;

  public DashboardController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @PostMapping()
  public Dashboard saveDashboard(@RequestBody @Valid Dashboard dashboard) {
    return dashboardService.saveDashboard(dashboard);
  }
}
