package net.pi.platform.hollywood.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date
import javax.validation.Valid
import javax.validation.constraints.Size


@Document(collection = "dashboards")
data class Dashboard(@Id val id: String?, @field:Size(min = 5, max = 60) val name: String, val hidden: Boolean?,
                     @field:Valid val widgets: List<Widget>, val position: Position, val bookmarked: Boolean, val image: String?, val link: Link?,
                     @CreatedDate val createdDate: Date?, @LastModifiedDate val lastModifiedDate: Date?
)
typealias JsonObject = Map<String, Any?>

data class Link(val url: String, val type: String?)
data class Position(val x: Int, val y: Int, val rows: Int, val cols: Int)


@JsonInclude(JsonInclude.Include.NON_NULL)
data class Widget(val id: String?, @field:Size(min = 5, max = 60) val name: String?, val type: String?, val bookmarked: Boolean,
                  val visualization: JsonObject?, val app: JsonObject?, val position: Position)

