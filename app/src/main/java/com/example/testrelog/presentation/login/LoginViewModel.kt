package com.example.testrelog.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrelog.common.UiEvents
import com.example.testrelog.domain.data.local.LoginState
import com.example.testrelog.domain.data.models.AuthResult
import com.example.testrelog.domain.data.models.RegistrationBody
import com.example.testrelog.domain.useCase.LoginUseCase
import com.example.testrelog.navigation.AppNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginUiState: MutableStateFlow<LoginUiState> =
        MutableStateFlow(LoginUiState.Initial)
    val loginUiState = _loginUiState.asStateFlow()

    private val _loginValueUiState = MutableStateFlow(LoginState())
    val loginValueUiState = _loginValueUiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun login(loginBody: RegistrationBody) {
        _loginUiState.value = LoginUiState.Loading

        viewModelScope.launch {
            try {
                val result = loginUseCase.login(loginBody)
                result.collect { authResult ->
                    when (authResult) {
                        is AuthResult.Success -> {
                            _loginUiState.value =
                                LoginUiState.Success(authResult.registrationResponse)
                            _eventFlow.emit(UiEvents.NavigateEvent(AppNavigation.Screen.Home.route))
                        }

                        is AuthResult.Error -> {
                            if (authResult.exception is HttpException && authResult.exception.code() == 401) {
                                val errorMessage = "Invalid credentials. Please check your username and password."
                                _loginUiState.value = LoginUiState.Error(errorMessage)
                                _eventFlow.emit(UiEvents.ToastEvent(message = errorMessage))
                            } else {
                                val errorMessage =
                                    authResult.exception.message ?: "An error occurred during login."
                                _loginUiState.value = LoginUiState.Error(errorMessage)
                                _eventFlow.emit(UiEvents.ToastEvent(message = errorMessage))
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                _loginUiState.value = LoginUiState.Error(e.message.toString())
                _eventFlow.emit(UiEvents.ToastEvent(message = e.message.toString()))
            }
        }
    }

    fun onEmailChange(newEmail: String) {
        _loginValueUiState.value = _loginValueUiState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _loginValueUiState.value = _loginValueUiState.value.copy(password = newPassword)
    }
}