package com.example.testrelog.domain.useCase

import com.example.testrelog.data.models.AuthResult
import com.example.testrelog.data.models.RegistrationBody
import com.example.testrelog.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : RegisterUseCase {

    override suspend fun register(registerRequest: RegistrationBody): Flow<AuthResult> {
        return withContext(Dispatchers.IO) {
            try {
                flow { emit(AuthResult.Success(repository.register(registerRequest))) }
            } catch (e: Exception) {
                flow { emit(AuthResult.Error(e)) }
            }
        }
    }
}

