package net.pi.platform.hollywood.repository

import net.pi.platform.hollywood.model.Dashboard
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface DashboardRepository : MongoRepository<Dashboard, String> {
    fun findOneById(id: String): Optional<Dashboard>
}
