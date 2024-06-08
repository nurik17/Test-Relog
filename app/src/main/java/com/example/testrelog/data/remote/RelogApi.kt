package com.example.testrelog.data.remote

import com.example.testrelog.data.models.RegistrationBody
import com.example.testrelog.domain.model.RegistrationResponse
import retrofit2.http.Body

import retrofit2.http.POST

interface RelogApi {

    @POST("auth/V1/signin")
    suspend fun login(
        @Body body: RegistrationBody
    ): RegistrationResponse

    @POST("auth/V1/signup")
    suspend fun register(
        @Body body: RegistrationBody
    ): RegistrationResponse

}