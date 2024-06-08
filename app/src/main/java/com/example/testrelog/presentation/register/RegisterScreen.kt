package com.example.testrelog.presentation.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel()
){

    val uiState = viewModel.registerUiState.collectAsStateWithLifecycle()
    val currentState = uiState.value

    Column(modifier = Modifier.fillMaxSize()) {
        when (currentState) {
            is RegisterUiState.Initial -> {
                // Initial UI elements, e.g., login/register buttons
            }

            is RegisterUiState.Loading -> {
                // Show loading indicator
            }

            is RegisterUiState.Success -> {
                // Handle successful login/register, e.g., navigate to main app
            }

            is RegisterUiState.Error -> {
                // Display error message
            }
        }
    }
}