package com.wizurd.deviantparser.client.dto

import com.google.gson.annotations.SerializedName

data class TokenDTO(
    @SerializedName("expires_in     ") val expires_in: Long = 0,
    @SerializedName("status         ") val status: String = "",
    @SerializedName("access_token   ") val access_token: String = "",
    @SerializedName("token_type     ") val token_type: String = "",
    @SerializedName("refresh_token  ") val refresh_token: String = "",
    @SerializedName("scope          ") val scope: String = ""
)