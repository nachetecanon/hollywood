package net.pi.platform.hollywood.controller


import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.service.DashboardService
import org.springframework.web.bind.annotation.*

import javax.validation.Valid


@RestController
@RequestMapping("dashboards")
class DashboardController(val dashboardService: DashboardService) {


    @PostMapping()
    fun saveDashboard(@RequestBody @Valid dashboard: Dashboard): Dashboard = dashboardService.saveDashboard(dashboard);


    @GetMapping()
    fun listAll(): List<Dashboard> = dashboardService.listAll();

}
