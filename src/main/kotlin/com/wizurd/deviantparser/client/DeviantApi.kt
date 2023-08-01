package com.wizurd.deviantparser.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DeviantApi(
    private val client: DeviantClient,
    @Value("\${app.deviant.client_id}")
    private val clientId: String,
    @Value("\${app.deviant.client_secret}")
    private val clientSecret: String
) {

    fun getToken() = client.getToken(clientId, clientSecret)

    fun getPictures(token: String, author: String) = client.getPictures(token, author)

    fun getContentPyPicture(token: String, pictureId: String) = client.getContent(token, pictureId)
}