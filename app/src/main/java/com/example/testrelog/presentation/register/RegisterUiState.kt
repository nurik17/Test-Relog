package com.example.testrelog.presentation.register

import com.example.testrelog.domain.model.RegistrationResponse

sealed interface RegisterUiState {
    object Initial : RegisterUiState
    object Loading : RegisterUiState
    data class Success(val registrationResponse: RegistrationResponse) : RegisterUiState
    data class Error(val error: Message) : RegisterUiState
}