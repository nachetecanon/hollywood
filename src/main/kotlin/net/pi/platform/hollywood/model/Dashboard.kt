package net.pi.platform.hollywood.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.Size

@Document(collection = "dashboards")
data class Dashboard(@Id val id: String?, @field:Size(min = 5, max = 60) val name: String, val hidden: Boolean?,
                     @field:Valid val widgets: List<Widget>, val position: Position, val bookmarked: Boolean, val image: String?, val link: Link?,
                     @CreatedDate val createdDate: Date?, @LastModifiedDate val lastModifiedDate: Date?
)
