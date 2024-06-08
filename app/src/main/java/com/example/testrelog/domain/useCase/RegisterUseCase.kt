package com.example.testrelog.domain.useCase

import com.example.testrelog.data.models.AuthResult
import com.example.testrelog.data.models.RegistrationBody
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {
    suspend fun register(registerRequest: RegistrationBody): Flow<AuthResult>
}