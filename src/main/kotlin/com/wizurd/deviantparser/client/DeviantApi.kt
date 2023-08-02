package com.wizurd.deviantparser.client

import com.wizurd.deviantparser.model.dictionary.EGrantType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DeviantApi(
    private val client: DeviantClient,
    @Value("\${app.deviant.client_id}")
    private val clientId: String,
    @Value("\${app.deviant.client_secret}")
    private val clientSecret: String,
    @Value("\${app.deviant.code}")
    private val code: String,
    @Value("\${app.deviant.redirect_uri}")
    private val redirectUri: String,
) {

    fun getTokenByCredentials() = client.getToken(clientId, clientSecret, EGrantType.CLIENT_CREDENTIALS.code)

    fun getTokenByAuthorization() =
        client.getToken(
            clientId = clientId,
            secret = clientSecret,
            grantType = EGrantType.AUTHORIZATION_CODE.code,
            code = code,
            redirectUri = redirectUri,
            scope = "browse"
        )

    fun getTokenByRefresh(refreshToken: String) =
        client.getToken(clientId, clientSecret, EGrantType.REFRESH_TOKEN.code, refreshToken)

    fun getPictures(token: String, author: String) = client.getPictures(token, author)

    fun getContentPyPicture(token: String, pictureId: String) = client.getContent(token, pictureId)
}