package net.pi.platform.hollywood.model.repository;

import net.pi.platform.hollywood.model.Dashboard;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DashboardRepository extends MongoRepository<Dashboard, String> {
}
