package com.example.testrelog.domain.useCase

import com.example.testrelog.domain.data.models.AuthResult
import com.example.testrelog.domain.data.models.RegistrationBody
import com.example.testrelog.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : LoginUseCase {
    override suspend fun login(loginRequest: RegistrationBody): Flow<AuthResult> {
        return withContext(Dispatchers.IO) {
            try {
                flow { emit(AuthResult.Success(repository.login(loginRequest))) }
            } catch (e: Exception) {
                flow { emit(AuthResult.Error(e)) }
            }
        }
    }
}
