package com.example.testrelog.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {

    val uiState = viewModel.loginUiState.collectAsStateWithLifecycle()
    val currentState = uiState.value

    Column(modifier = Modifier.fillMaxSize()) {
        when (currentState) {
            is LoginUiState.Initial -> {
                // Initial UI elements, e.g., login/register buttons
            }

            is LoginUiState.Loading -> {
                // Show loading indicator
            }

            is LoginUiState.Success -> {
                // Handle successful login/register, e.g., navigate to main app
            }

            is LoginUiState.Error -> {
                // Display error message
            }
        }
    }
}