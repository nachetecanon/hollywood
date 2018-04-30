package net.pi.platform.hollywood.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    @Bean
    fun restTemplate() =  RestTemplateBuilder().build()
}