package com.wizurd.deviantparser.client

import com.wizurd.deviantparser.client.dto.GalleryDTO
import com.wizurd.deviantparser.client.dto.TokenDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import com.wizurd.deviantparser.client.config.FeignConfig
import com.wizurd.deviantparser.client.dto.DeviantDownloadContentDTO
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("https://www.deviantart.com", configuration = [FeignConfig::class])
interface DeviantClient {

    @GetMapping("/api/v1/oauth2/gallery/all")
    fun getPictures(
        @RequestParam("access_token") token: String,
        @RequestParam username: String,
        @RequestParam("mature_content") mature: Boolean = true,
    ): GalleryDTO

    @GetMapping("/oauth2/token")
    fun getToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") secret: String,
        @RequestParam("grant_type") grantType: String,
        @RequestParam code: String? = null,
        @RequestParam("redirect_uri") redirectUri: String? = null,
        @RequestParam scope: String? = null,
        @RequestParam("refresh_token") refreshToken: String? = null
    ): TokenDTO



    @GetMapping("/api/v1/oauth2/deviation/download/{deviationid}")
    fun getContent(
        @RequestParam("access_token") token: String,
        @PathVariable("deviationid") pictureId: String,
        @RequestParam("mature_content") mature: Boolean = true,
    ): DeviantDownloadContentDTO
}