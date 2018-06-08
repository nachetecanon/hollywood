package net.pi.platform.hollywood.controller

import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.service.AuthorizationService
import net.pi.platform.hollywood.service.DashboardService
import org.keycloak.KeycloakSecurityContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.ServletRequest

import javax.validation.Valid

@RestController
@RequestMapping("dashboards")
class DashboardController(val dashboardService: DashboardService, val authorizationService: AuthorizationService) {

    @PostMapping
    fun saveDashboard(@RequestBody @Valid dashboard: Dashboard): Dashboard = dashboardService.createDashboard(dashboard)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun saveDashboard(@PathVariable("id", required = true) id: String,
                      @RequestBody @Valid dashboard: Dashboard) = dashboardService.updateDashboard(id, dashboard)

    @GetMapping
    fun listAll(servletRequest: ServletRequest): List<Dashboard> {
        if (authorizationService.authEnabled) {
            return onlyAuthorizedDasboards(servletRequest)
        } else {
            return dashboardService.listAll()
        }
    }

    private fun onlyAuthorizedDasboards(request: ServletRequest): List<Dashboard> {
        val token = extractToken(request)
        val ids = authorizationService.fetchAuthorizedResources(token, "hollywood", "dashboard").map { it.resource }
        return dashboardService.listByIds(ids)
    }

    private fun extractToken(request: ServletRequest): String {
        val keycloakSecurityContext = request.getAttribute(KeycloakSecurityContext::class.java.name) as KeycloakSecurityContext
        return keycloakSecurityContext.tokenString
    }
}
