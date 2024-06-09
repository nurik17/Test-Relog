package com.example.testrelog.presentation.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testrelog.R
import com.example.testrelog.common.AuthScreenTopBlock
import com.example.testrelog.common.CustomButton
import com.example.testrelog.common.ProgressBlock
import com.example.testrelog.common.RegistrationTextField
import com.example.testrelog.common.SpannableNavigateText
import com.example.testrelog.common.UiEvents
import com.example.testrelog.domain.data.models.RegistrationBody
import com.example.testrelog.ui.theme.Grey900
import com.example.testrelog.ui.theme.typography
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navigateToLogin:() ->Unit,
) {

    val uiState = viewModel.registerUiState.collectAsStateWithLifecycle()
    val currentState = uiState.value
    val registerUiValue = viewModel.registerUiValue.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.NavigateEvent -> {
                    navigateToLogin()
                }
                is UiEvents.ToastEvent ->{
                    Toast.makeText(context,event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when (currentState) {
            is RegisterUiState.Initial -> {
                RegisterScreenContent(
                    email = registerUiValue.value.email,
                    password = registerUiValue.value.password,
                    confirmPassword = registerUiValue.value.confirmPassword,
                    onEmailValueChanged = {
                        viewModel.onEmailChange(it)
                    },
                    onPasswordValueChanged = {
                                             viewModel.onPasswordChange(it)
                    },
                    onRegisterButtonClick = {
                        val registrationBody = RegistrationBody(
                            email = registerUiValue.value.email,
                            password = registerUiValue.value.password
                        )
                        viewModel.register(registrationBody)
                    },
                    navigation = navigateToLogin,
                    onConfirmPasswordValueChanged = {
                        viewModel.onConfirmPasswordChange(it)
                    }
                )
            }

            is RegisterUiState.Loading -> {
                ProgressBlock()
            }

            is RegisterUiState.Success -> {
                navigateToLogin()
            }

            is RegisterUiState.Error -> {
                RegisterScreenContent(
                    email = registerUiValue.value.email,
                    password = registerUiValue.value.password,
                    confirmPassword = registerUiValue.value.confirmPassword,
                    onEmailValueChanged = {
                        viewModel.onEmailChange(it)
                    },
                    onPasswordValueChanged = {
                        viewModel.onPasswordChange(it)
                    },
                    onRegisterButtonClick = {
                        val registrationBody = RegistrationBody(
                            email = registerUiValue.value.email,
                            password = registerUiValue.value.password
                        )
                        viewModel.register(registrationBody)
                    },
                    navigation = navigateToLogin,
                    onConfirmPasswordValueChanged = {
                        viewModel.onConfirmPasswordChange(it)
                    },
                )
            }
        }
    }
}

@Composable
fun RegisterScreenContent(
    email: String,
    password: String,
    confirmPassword: String,
    onEmailValueChanged: (String) -> Unit,
    onPasswordValueChanged: (String) -> Unit,
    onConfirmPasswordValueChanged: (String) -> Unit,
    onRegisterButtonClick:() ->Unit,
    navigation:() ->Unit,
) {
    var showPasswordValue by remember { mutableStateOf(value = false) }
    var confirmShowPasswordValue by remember { mutableStateOf(value = false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        AuthScreenTopBlock(
            onBackIconClick = { /*TODO*/ },
            mainText = stringResource(id = R.string.follow),
            subText = stringResource(id = R.string.fill_the_information)
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.email),
            style = typography.l24sfD700,
            fontSize = 14.sp,
            color = Grey900
        )
        RegistrationTextField(
            onValueChanged = onEmailValueChanged,
            hint = stringResource(id = R.string.your_email),
            value = email,
            leadingIcon = R.drawable.ic_message,
            showPassword = true,
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.password),
            style = typography.l24sfD700,
            fontSize = 14.sp,
            color = Grey900
        )
        RegistrationTextField(
            onValueChanged = onPasswordValueChanged,
            value = password,
            hint = stringResource(id = R.string.your_password),
            leadingIcon = R.drawable.ic_message,
            showPassword = showPasswordValue,
            trailingIcon = {
                if (showPasswordValue) {
                    IconButton(onClick = { showPasswordValue = false }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_show_password),
                            contentDescription = "show_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPasswordValue = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_show_password),
                            contentDescription = "hide_password"
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.repeat_password),
            style = typography.l24sfD700,
            fontSize = 14.sp,
            color = Grey900
        )
        RegistrationTextField(
            onValueChanged = onConfirmPasswordValueChanged,
            value = confirmPassword,
            showPassword = showPasswordValue,
            hint = stringResource(id = R.string.your_password),
            leadingIcon = R.drawable.ic_message,
            trailingIcon = {
                if (showPasswordValue) {
                    IconButton(onClick = { showPasswordValue = false }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_show_password),
                            contentDescription = "show_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPasswordValue = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_show_password),
                            contentDescription = "hide_password"
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            text = stringResource(id = R.string.enter),
            onButtonClick = onRegisterButtonClick
        )
        Spacer(modifier = Modifier.height(16.dp))
        SpannableNavigateText(
            subText = stringResource(id = R.string.do_you_have_account),
            navigation = navigation,
            annotatedString = stringResource(id = R.string.follow)
        )
    }
}
