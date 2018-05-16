package net.pi.platform.hollywood

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.data.mongodb.config.EnableMongoAuditing


@SpringBootApplication(scanBasePackages = arrayOf("net.pi.platform"))
@EnableMongoAuditing
class Application : SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}