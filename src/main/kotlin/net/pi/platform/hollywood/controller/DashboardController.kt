package net.pi.platform.hollywood.controller

import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.service.DashboardService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("dashboards")
class DashboardController(val dashboardService: DashboardService) {


    @PostMapping()
    fun saveDashboard(@RequestBody @Valid dashboard: Dashboard): Dashboard = dashboardService.saveDashboard(dashboard);

}
