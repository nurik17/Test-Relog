package com.example.testrelog.data.models

import com.google.gson.annotations.SerializedName

data class RegistrationBody(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)