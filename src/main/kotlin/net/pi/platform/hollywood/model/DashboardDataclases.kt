package net.pi.platform.hollywood.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

typealias JsonObject = Map<String, Any?>

@Document(collection = "dashboards")
data class Dashboard(@Id val id: String?, val name: String, val widgets: List<Widget>);

data class Widget(val id: String?, val name: String, val bookmarked: Boolean, val visualization: JsonObject?, val position: WidgetPosition)

/**
 * x :Position in the horizontal axis
 * y :  Position in the vertical axis
 * rows :number of rows where the widget is allocated a widget
 * cols  : number of rows where the widget is allocated a widget
 */
data class WidgetPosition(val x: Int, val y: Int, val rows: Int, val cols: Int);
