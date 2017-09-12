package net.pi.platform.hollywood.service

import net.pi.platform.common.api.exception.EntityNotFoundException
import net.pi.platform.common.api.exception.WrongInputValueException
import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.repository.DashboardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class DashboardService(@Autowired val dashboardRepository: DashboardRepository) {

    private fun saveDashboard(dashboard: Dashboard): Dashboard {
        return dashboardRepository.save(dashboard)
    }

    /**
     * Method to create new dashboards
     * @param dashboard: new dashboard
     * @return created dashboard
     */
    fun createDashboard(dashboard: Dashboard): Dashboard {
        if (!dashboard.id.isNullOrBlank()) {
            throw WrongInputValueException("You cannot create a dashboard with id")
        }
        return saveDashboard(dashboard)
    }

    /**
     * Method to update dashboards identified by id
     * @param id: dashboard id
     * @param dashboard: resource to update
     */
    fun updateDashboard(id: String?, dashboard: Dashboard): Unit {
        if (id.isNullOrEmpty()) {
            throw WrongInputValueException("You cannot update a dashboard without id")
        }
        if (findOne(id) == null) {
            throw EntityNotFoundException("Unable to update dashboard: Dashboard with id[" + id + "] not found")
        }
        saveDashboard(dashboard.copy(id = id))
    }

    /**
     * Return all dashboards
     * @return list of dashboards
     */
    fun listAll(): List<Dashboard> = dashboardRepository.findAll()

    /**
     * Return a dashboard identified by id
     * @param id: dashboard id
     * @return dashboard
     */
    fun findOne(id: String?) = dashboardRepository.findOne(id)
}
