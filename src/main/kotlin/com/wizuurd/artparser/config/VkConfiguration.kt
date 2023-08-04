package com.wizuurd.artparser.config

import com.wizuurd.artparser.client.vk.VkClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VkConfiguration(
    private val vkClient: VkClient
) {

    @Bean
    fun vkInit() = vkClient.startPolling()
}