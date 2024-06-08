package com.example.testrelog.domain.model

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("email") val email: String,
    @SerializedName("id") val id: Int,
    @SerializedName("roles") val roles: List<String>,
    @SerializedName("tokenType") val tokenType: String,
    @SerializedName("username") val username: String,
)