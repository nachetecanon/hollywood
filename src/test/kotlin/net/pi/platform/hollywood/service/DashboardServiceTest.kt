package net.pi.platform.hollywood.service

import junit.framework.Assert.assertEquals
import net.pi.platform.hollywood.repository.DashboardRepository
import net.pi.platform.hollywood.DataSamplesObjects
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

}
