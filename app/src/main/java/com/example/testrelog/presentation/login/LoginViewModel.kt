package com.example.testrelog.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrelog.data.models.AuthResult
import com.example.testrelog.data.models.RegistrationBody
import com.example.testrelog.domain.useCase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginUiState: MutableStateFlow<LoginUiState> =
        MutableStateFlow(LoginUiState.Initial)
    val loginUiState = _loginUiState.asStateFlow()

    fun login(loginBody: RegistrationBody) =
        viewModelScope.launch(Dispatchers.IO) {
            _loginUiState.value = LoginUiState.Loading
            val result = loginUseCase.login(loginBody)

            result.collect { authResult ->
                when (authResult) {
                    is AuthResult.Success -> _loginUiState.value =
                        LoginUiState.Success(authResult.registrationResponse)

                    is AuthResult.Error -> _loginUiState.value =
                        LoginUiState.Error(authResult.exception)
                }
            }
        }
}