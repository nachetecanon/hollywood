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

    private val logger = LoggerFactory.getLogger(DashboardController::class.java)

    @PostMapping
    fun saveDashboard(@RequestBody @Valid dashboard: Dashboard): Dashboard = dashboardService.createDashboard(dashboard)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun saveDashboard(@PathVariable("id", required = true) id: String,
                      @RequestBody @Valid dashboard: Dashboard) = dashboardService.updateDashboard(id, dashboard)

    @GetMapping
    fun listAll(servletRequest: ServletRequest): List<Dashboard> {
        return if (authorizationService.authEnabled) {
            onlyAuthorizedDasboards(servletRequest)
        } else {
            dashboardService.listAll()
        }
    }

    private fun onlyAuthorizedDasboards(request: ServletRequest): List<Dashboard> {
        val token = extractToken(request)
        val resources = authorizationService.fetchAuthorizedResources(token, "hollywood", "dashboards")
        val ids = resources.map { it.id }
        val dashboards = dashboardService.listByIds(ids)
        if (ids.size > dashboards.size) {
            val notFoundDasboards = ids.toHashSet().minus(dashboards.map { it.id })
            logger.warn("Not Found dashboards ${notFoundDasboards} for ${token}")
        }
        return dashboards
    }

    private fun extractToken(request: ServletRequest): String {
        val keycloakSecurityContext = request.getAttribute(KeycloakSecurityContext::class.java.name) as KeycloakSecurityContext
        return keycloakSecurityContext.tokenString
    }
}
