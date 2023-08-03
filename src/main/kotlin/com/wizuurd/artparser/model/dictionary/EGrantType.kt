package com.wizuurd.artparser.model.dictionary

enum class EGrantType(val code: String) {
    AUTHORIZATION_CODE("authorization_code"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token")
}