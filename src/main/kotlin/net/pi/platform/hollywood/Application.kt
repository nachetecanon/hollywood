package net.pi.platform.hollywood

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = arrayOf("net.pi.platform"))
@EnableMongoAuditing
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}