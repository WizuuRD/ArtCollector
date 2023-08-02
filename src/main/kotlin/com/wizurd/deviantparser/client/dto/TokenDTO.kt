package com.wizurd.deviantparser.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenDTO(
    @JsonProperty("expires_in"   ) val expiresIn    : Int,
    @JsonProperty("status"       ) val status       : String,
    @JsonProperty("access_token" ) val accessToken  : String,
    @JsonProperty("token_type"   ) val tokenType    : String,
    @JsonProperty("refresh_token") val refreshToken : String? = null,
    @JsonProperty("scope"        ) val scope        : String? = null,
)