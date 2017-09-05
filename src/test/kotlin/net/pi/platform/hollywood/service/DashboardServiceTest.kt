package net.pi.platform.hollywood.service

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import net.pi.platform.common.api.exception.WrongInputValueException
import net.pi.platform.hollywood.DataSamplesObjects
import net.pi.platform.hollywood.repository.DashboardRepository
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*

class DashboardServiceImplTest() {

    val dashboardRepository: DashboardRepository = mock(DashboardRepository::class.java)
    val dashboardService: DashboardService = DashboardService(dashboardRepository)

    @Test
    fun `test save`() {
        val dashboard = DataSamplesObjects.getDashboard();
        given(dashboardRepository.save(dashboard)).willReturn(dashboard)
        val dashboardReturned = dashboardService.saveDashboard(dashboard)
        assertEquals(dashboardReturned, dashboard)
        verify<DashboardRepository>(this.dashboardRepository, times(1)).save(dashboard)
        verifyNoMoreInteractions(this.dashboardRepository)
    }

    @Test
    fun `trying to save dasboard with id`() {
        val dashboard = DataSamplesObjects.getDashboard().copy(id = "blablabla")
        try {
            dashboardService.saveDashboard(dashboard)
        } catch (e: WrongInputValueException) {
            assertEquals(e.message, "You cannot save one dashboard with id")
            verifyNoMoreInteractions(this.dashboardRepository)
        }

    }

    @Test
    fun `test list all dasboards`() {
        val dashboard = DataSamplesObjects.getDashboard();
        given(dashboardRepository.findAll()).willReturn(listOf(dashboard))
        val dashboards = dashboardService.listAll();
        assertEquals(dashboards.size, 1);
        assertTrue(dashboards.equals(listOf(dashboard)))
        verify<DashboardRepository>(this.dashboardRepository, times(1)).findAll()
        verifyNoMoreInteractions(this.dashboardRepository)
    }

}
