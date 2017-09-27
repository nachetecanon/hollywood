package net.pi.platform.hollywood

import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.model.Widget
import net.pi.platform.hollywood.model.Position

class DataSamplesObjects {

    companion object Dashboards {

        fun getDashboard(): Dashboard {
            return Dashboard(
                    id = null,
                    name = "longerName",
                    widgets = listOf(Widget(id = null, name = "name-widget", bookmarked = true, visualization = null,
                            position = Position(x = 0, y = 1, rows = 2, cols = 2))),
                    position = Position(x = 0, y = 1, rows = 2, cols = 2)
            )
        }


        fun getDashboardWithVisualization(): Dashboard {
            return getDashboard().copy(widgets = listOf(
                    Widget(id = null, name = "name-widget"
                            , bookmarked = true,
                            visualization = hashMapOf("someParameter" to 1,
                                    "otherValue" to Position(x = 0, y = 1, rows = 2, cols = 2)),
                            position = Position(x = 0, y = 1, rows = 2, cols = 2))))
        }


        fun getDashboardWithUknownVisualizationJsonString() = """ {
	"name": "Testing dashboard",
	"widgets": [{
		"bookmarked": false,
		"name": "First widget",
        "visualization" : {
            "someParameter" : 1,
            "otherParameter" : 3
        },
		"position": {
			"x": 0,
			"y": 0,
			"cols": 1,
			"rows": 2
		}
	}, {
		"bookmarked": true,
		"name": "Block 1",
        "visualization" : {
            "someParameter" : 1,
            "otherParameter" : {
            "a" : 3
            }
        },
		"position": {
			"x": 1,
			"y": 0,
			"cols": 1,
			"rows": 1
		}
	}],
    "position": { "x": 0, "y": 0, "cols": 1, "rows": 2 }
    }
        """.trimIndent()
    }
}



