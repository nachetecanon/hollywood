package net.pi.platform.hollywood

import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.model.Widget
import net.pi.platform.hollywood.model.WidgetPosition

class DataSamplesObjects {

    companion object Dashboards {

        fun getDashboard(): Dashboard {
            return Dashboard(id = "my-d",
                    name = "name",
                    widgets = listOf(
                            Widget(id = null, name = "name-widget"
                                    , bookmarked = true,
                                    position = WidgetPosition(x = 0, y = 1, rows = 2, cols = 2))))
        }
    }
}



