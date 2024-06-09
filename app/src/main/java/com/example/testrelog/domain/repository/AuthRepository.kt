package com.example.testrelog.domain.repository

import com.example.testrelog.domain.data.models.RegistrationBody
import com.example.testrelog.domain.model.RegistrationResponse

interface AuthRepository {
    suspend fun login(loginRequest: RegistrationBody): RegistrationResponse
    suspend fun register(registerRequest: RegistrationBody): RegistrationResponse
}