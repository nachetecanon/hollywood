package net.pi.platform.hollywood.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "dashboards")
data class Dashboard(@Id val id: String?, val name: String, val widgets: List<Widget>);

data class Widget(val id: String?, val name: String, val bookmarked: Boolean, val widgetPosition: WidgetPosition)

data class WidgetPosition(val x: Int, val y: Int, val rows: Int, val cols: Int);
