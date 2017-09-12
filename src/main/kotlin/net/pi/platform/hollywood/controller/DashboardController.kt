package net.pi.platform.hollywood.controller


import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.service.DashboardService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

import javax.validation.Valid


@RestController
@RequestMapping("dashboards")
class DashboardController(val dashboardService: DashboardService) {

    @PostMapping
    fun saveDashboard(@RequestBody @Valid dashboard: Dashboard): Dashboard =
        dashboardService.createDashboard(dashboard);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun saveDashboard(@PathVariable("id", required = true) id: String,
                      @RequestBody @Valid dashboard: Dashboard) =
        dashboardService.updateDashboard(id, dashboard)

    @GetMapping
    fun listAll(): List<Dashboard> = dashboardService.listAll();

}
