package net.pi.platform.hollywood.service

import net.pi.platform.common.api.exception.WrongInputValueException
import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.repository.DashboardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class DashboardService(@Autowired val dashboardRepository: DashboardRepository) {

   
    fun saveDashboard(dashboard: Dashboard): Dashboard {
        if (dashboard.id != null) {
            throw WrongInputValueException("You cannot save one dashboard with id")
        }
        val a = Exception();
        return dashboardRepository.save(dashboard);
    }

    fun listAll(): List<Dashboard> = dashboardRepository.findAll();
}
