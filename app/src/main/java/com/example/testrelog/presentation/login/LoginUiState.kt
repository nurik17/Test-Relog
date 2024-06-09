package com.example.testrelog.presentation.login

import com.example.testrelog.domain.model.RegistrationResponse

sealed interface LoginUiState {
    object Initial : LoginUiState
    object Loading : LoginUiState
    data class Success(val registrationResponse: RegistrationResponse) : LoginUiState
    data class Error(val message: String) : LoginUiState
}