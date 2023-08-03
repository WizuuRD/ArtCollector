package com.wizuurd.artparser.client.deviant.config

import feign.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun logger(): Logger {
        return FeignLogger()
    }
}