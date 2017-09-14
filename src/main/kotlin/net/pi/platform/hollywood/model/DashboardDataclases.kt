package net.pi.platform.hollywood.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.Valid
import javax.validation.constraints.Size

typealias JsonObject = Map<String, Any?>

@Document(collection = "dashboards")
data class Dashboard(@Id val id: String?, @field:Size(min = 5, max = 60) val name: String, @field:Valid val widgets: List<Widget>);

data class Widget(val id: String?, @field:Size(min = 5, max = 60) val name: String, val bookmarked: Boolean, val visualization: JsonObject?, val position: WidgetPosition)

/**
 * x :Position in the horizontal axis
 * y :  Position in the vertical axis
 * rows :number of rows where the widget is allocated a widget
 * cols  : number of rows where the widget is allocated a widget
 */
data class WidgetPosition(val x: Int, val y: Int, val rows: Int, val cols: Int);
