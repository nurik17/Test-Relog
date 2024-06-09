package com.example.testrelog.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrelog.domain.data.local.RegisterState
import com.example.testrelog.domain.data.models.AuthResult
import com.example.testrelog.domain.data.models.RegistrationBody
import com.example.testrelog.domain.useCase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _registerUiState: MutableStateFlow<RegisterUiState> =
        MutableStateFlow(RegisterUiState.Initial)
    val registerUiState = _registerUiState.asStateFlow()

    private val _registerUiValue = MutableStateFlow(RegisterState())
    val registerUiValue = _registerUiValue.asStateFlow()

    fun register(registrationBody: RegistrationBody) {
        viewModelScope.launch {
            _registerUiState.value = RegisterUiState.Loading
            val result = registerUseCase.register(registrationBody)

            result.collect { authResult ->
                when (authResult) {
                    is AuthResult.Success -> _registerUiState.value =
                        RegisterUiState.Success(authResult.registrationResponse)

                    is AuthResult.Error -> _registerUiState.value =
                        RegisterUiState.Error(authResult.exception)
                }
            }
        }
    }

    fun onEmailChange(newEmail: String) {
        _registerUiValue.value = _registerUiValue.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _registerUiValue.value = _registerUiValue.value.copy(password = newPassword)
    }
}