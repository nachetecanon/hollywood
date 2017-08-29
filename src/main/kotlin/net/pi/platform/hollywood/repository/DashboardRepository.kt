package net.pi.platform.hollywood.repository;

import net.pi.platform.hollywood.model.Dashboard
import org.springframework.data.mongodb.repository.MongoRepository


interface DashboardRepository : MongoRepository<Dashboard, String> {

}




