package net.pi.platform.hollywood.model

import javax.validation.constraints.Size

typealias JsonObject = Map<String, Any?>

data class Widget(val id: String?, @field:Size(min = 5, max = 60) val name: String, val bookmarked: Boolean,
                  val visualization: JsonObject?, val app: JsonObject?, val position: Position)
