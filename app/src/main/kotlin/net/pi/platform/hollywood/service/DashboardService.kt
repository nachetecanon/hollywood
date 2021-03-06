package net.pi.platform.hollywood.service

import net.pi.platform.common.api.exception.EntityNotFoundException
import net.pi.platform.common.api.exception.WrongInputValueException
import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.repository.DashboardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DashboardService(@Autowired val dashboardRepository: DashboardRepository) {

    private fun saveDashboard(dashboard: Dashboard): Dashboard {
        return dashboardRepository.save(dashboard)
    }

    fun createDashboard(dashboard: Dashboard): Dashboard {
        if (!dashboard.id.isNullOrBlank()) {
            throw WrongInputValueException("You cannot create a dashboard with id")
        }
        return saveDashboard(dashboard)
    }

    fun updateDashboard(id: String, dashboard: Dashboard) {
        dashboardRepository.findOneById(id) ?:
            throw EntityNotFoundException("Unable to update dashboard: Dashboard with id[$id] not found")
        saveDashboard(dashboard.copy(id = id))
    }

    fun deleteDashboard(id: String){
        if (dashboardRepository.existsById(id)) {
            dashboardRepository.deleteById(id)
        } else {
            throw EntityNotFoundException("Unable to delete dashboard: Dashboard with id[$id] not found")
        }
    }

    fun listAll(): List<Dashboard> = dashboardRepository.findAll()

    fun listByIds(ids: List<String>): List<Dashboard> = dashboardRepository.findAllById(ids).filterNotNull()
}
