package com.wizurd.deviantparser.client

import com.wizurd.deviantparser.client.dto.GalleryDTO
import com.wizurd.deviantparser.client.dto.TokenDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import com.wizurd.deviantparser.client.config.FeignConfig

@Component
@FeignClient("https://www.deviantart.com", configuration = [FeignConfig::class])
interface DeviantClient {

    @GetMapping("/api/v1/oauth2/gallery/all")
    fun test(
        @RequestParam("access_token") token: String,
        @RequestParam username: String
    ): GalleryDTO

    @GetMapping("/oauth2/authorize")
    fun authorize(
        @RequestParam response_type: String = "code",
        @RequestParam client_id: Long,
        @RequestParam redirect_uri: String
    ): String

    @GetMapping("/oauth2/token")
    fun getToken(
        @RequestParam client_id: Long,
        @RequestParam client_secret: String,
        @RequestParam grant_type: String = "client_credentials",
    ): TokenDTO

    @GetMapping("/api/v1/oauth2/deviation/download/{deviationid}")
    fun getContent()
}