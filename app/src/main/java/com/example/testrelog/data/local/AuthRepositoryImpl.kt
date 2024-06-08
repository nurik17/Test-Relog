package com.example.testrelog.data.local

import com.example.testrelog.data.models.RegistrationBody
import com.example.testrelog.data.remote.RelogApi
import com.example.testrelog.domain.model.RegistrationResponse
import com.example.testrelog.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val relogApi: RelogApi,
) : AuthRepository {
    override suspend fun login(loginRequest: RegistrationBody): RegistrationResponse {
        return relogApi.login(loginRequest)
    }

    override suspend fun register(registerRequest: RegistrationBody): RegistrationResponse {
        return relogApi.register(registerRequest)
    }
}