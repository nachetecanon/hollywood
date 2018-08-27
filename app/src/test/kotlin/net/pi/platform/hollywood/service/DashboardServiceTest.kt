package net.pi.platform.hollywood.service

import net.pi.platform.common.api.exception.EntityNotFoundException
import net.pi.platform.common.api.exception.WrongInputValueException
import net.pi.platform.common.api.exception.errorcode.APIError
import net.pi.platform.hollywood.DataSamplesObjects
import net.pi.platform.hollywood.repository.DashboardRepository
import org.junit.Assert.*
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
        val dashboard = DataSamplesObjects.getDashboardWithoutId()
        given(dashboardRepository.save(dashboard)).willReturn(dashboard)
        val dashboardReturned = dashboardService.createDashboard(dashboard)
        assertEquals(dashboardReturned, dashboard)
        verify<DashboardRepository>(this.dashboardRepository, times(1)).save(dashboard)
        verifyNoMoreInteractions(this.dashboardRepository)
    }

    @Test
    fun `test create dashboard with id`() {
        val dashboard = DataSamplesObjects.getDashboard("blablabla")
        try {
            dashboardService.createDashboard(dashboard)
        } catch (e: WrongInputValueException) {
            assertEquals(e.message, "You cannot create a dashboard with id")
            verifyNoMoreInteractions(this.dashboardRepository)
        }
    }

    @Test
    fun `test list all dashboards`() {
        val dashboard = DataSamplesObjects.getDashboardWithoutId()
        given(dashboardRepository.findAll()).willReturn(listOf(dashboard))
        val dashboards = dashboardService.listAll()
        assertEquals(dashboards.size, 1)
        assertTrue(dashboards.equals(listOf(dashboard)))
        verify<DashboardRepository>(this.dashboardRepository, times(1)).findAll()
        verifyNoMoreInteractions(this.dashboardRepository)
    }

    @Test
    fun `test update dashboard`() {
        val dashboard = DataSamplesObjects.getDashboardWithoutId()
        val dashboardToUpdate = dashboard.copy(id = "some_id")
        given(dashboardRepository.save(dashboardToUpdate)).willReturn(dashboardToUpdate)
        given(dashboardRepository.findOneById(ArgumentMatchers.anyString())).willReturn(dashboard)
        dashboardService.updateDashboard("some_id", dashboard)
        verify<DashboardRepository>(this.dashboardRepository, times(1)).save(dashboardToUpdate)
    }

    @Test
    fun `test delete dashboard`() {
        val dashboardId =  "some_id"
        given(dashboardRepository.existsById(dashboardId)).willReturn(true);
        dashboardService.deleteDashboard("some_id")
        verify<DashboardRepository>(this.dashboardRepository, times(1)).deleteById(dashboardId)
        verify<DashboardRepository>(this.dashboardRepository, times(1)).existsById(dashboardId)
        verifyNoMoreInteractions(this.dashboardRepository)
    }

    @Test
    fun `test delete dashboard thown an exception`() {
        val dashboardId =  "some_id"
        given(dashboardRepository.existsById(dashboardId)).willReturn(false);
        try {
            dashboardService.deleteDashboard("some_id")
            fail("Should thow a EntityNotFoundException")
        } catch (e : EntityNotFoundException){
            assertEquals(e.error,APIError.NOT_FOUND)
        } finally {
            verify<DashboardRepository>(this.dashboardRepository, times(1)).existsById(dashboardId)
        }

    }

    @Test
    fun `test update dashboard which not exists`() {
        val dashboard = DataSamplesObjects.getDashboardWithoutId()
        given(dashboardRepository.findOneById(ArgumentMatchers.anyString())).willReturn(null)
        try {
            dashboardService.updateDashboard("some_id", dashboard)
        } catch (e: EntityNotFoundException) {
            assertEquals(e.message, "Unable to update dashboard: Dashboard with id[some_id] not found")
            verify<DashboardRepository>(this.dashboardRepository, times(1))
                    .findOneById(anyString())
        }
    }

}
