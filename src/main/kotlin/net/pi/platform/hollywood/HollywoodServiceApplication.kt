package net.pi.platform.hollywood

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class HollywoodServiceApplication {


}

fun main(args: Array<String>) {
    SpringApplication.run(HollywoodServiceApplication::class.java, *args)
}
