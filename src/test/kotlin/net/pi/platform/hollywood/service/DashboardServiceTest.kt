package net.pi.platform.hollywood.service

import net.pi.platform.common.api.exception.EntityNotFoundException
import net.pi.platform.common.api.exception.WrongInputValueException
import net.pi.platform.hollywood.DataSamplesObjects
import net.pi.platform.hollywood.repository.DashboardRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*
import java.util.*

class DashboardServiceImplTest {

    val dashboardRepository: DashboardRepository = mock(DashboardRepository::class.java)
    val dashboardService: DashboardService = DashboardService(dashboardRepository)

    @Test
    fun `test create dashboard`() {
        val dashboard = DataSamplesObjects.getDashboard()
        given(dashboardRepository.save(dashboard)).willReturn(dashboard)
        val dashboardReturned = dashboardService.createDashboard(dashboard)
        assertEquals(dashboardReturned, dashboard)
        verify<DashboardRepository>(this.dashboardRepository, times(1)).save(dashboard)
        verifyNoMoreInteractions(this.dashboardRepository)
    }

    @Test
    fun `test create dashboard with id`() {
        val dashboard = DataSamplesObjects.getDashboard().copy(id = "blablabla")
        try {
            dashboardService.createDashboard(dashboard)
        } catch (e: WrongInputValueException) {
            assertEquals(e.message, "You cannot create a dashboard with id")
            verifyNoMoreInteractions(this.dashboardRepository)
        }
    }

    @Test
    fun `test list all dashboards`() {
        val dashboard = DataSamplesObjects.getDashboard()
        given(dashboardRepository.findAll()).willReturn(listOf(dashboard))
        val dashboards = dashboardService.listAll()
        assertEquals(dashboards.size, 1)
        assertTrue(dashboards.equals(listOf(dashboard)))
        verify<DashboardRepository>(this.dashboardRepository, times(1)).findAll()
        verifyNoMoreInteractions(this.dashboardRepository)
    }

    @Test
    fun `test update dashboard`() {
        val dashboard = DataSamplesObjects.getDashboard()
        val dashboardToUpdate = dashboard.copy(id = "some_id")
        given(dashboardRepository.save(dashboardToUpdate)).willReturn(dashboardToUpdate)
        given(dashboardRepository.findOneById(ArgumentMatchers.anyString())).willReturn(Optional.of(dashboard))
        dashboardService.updateDashboard("some_id", dashboard)
        verify<DashboardRepository>(this.dashboardRepository, times(1)).save(dashboardToUpdate)
    }

    @Test
    fun `test update dashboard which not exists`() {
        val dashboard = DataSamplesObjects.getDashboard()
        given(dashboardRepository.findOneById(ArgumentMatchers.anyString())).willReturn(Optional.empty())
        try {
            dashboardService.updateDashboard("some_id", dashboard)
        } catch (e: EntityNotFoundException) {
            assertEquals(e.message, "Unable to update dashboard: Dashboard with id[some_id] not found")
            verify<DashboardRepository>(this.dashboardRepository, times(1))
                    .findOneById(anyString())
        }
    }

}
