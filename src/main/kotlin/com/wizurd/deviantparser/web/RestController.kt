package com.wizurd.deviantparser.web

import com.wizurd.deviantparser.client.DeviantClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/testing/")
class RestController(
    private val deviantClient: DeviantClient
) {

    @GetMapping("/test")
    fun testing(author: String) {
        val token = deviantClient.getToken()
        val response = deviantClient.test(token.access_token, author)
        println(response.results.map { it.preview?.src }.joinToString())
    }
}