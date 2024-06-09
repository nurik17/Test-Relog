package com.example.testrelog.domain.data.models

import com.example.testrelog.domain.model.RegistrationResponse

sealed class AuthResult {
    data class Success(val registrationResponse: RegistrationResponse) : AuthResult()
    data class Error(val exception: Throwable) : AuthResult()
}