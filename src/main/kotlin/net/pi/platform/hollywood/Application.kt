package net.pi.platform.hollywood

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer


@SpringBootApplication(scanBasePackages = arrayOf("net.pi.platform"))
class Application : SpringBootServletInitializer() {

    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}
