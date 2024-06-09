package com.example.testrelog.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome:() ->Unit,
    navigateToRegister:() ->Unit,
) {

    val uiState = viewModel.loginUiState.collectAsStateWithLifecycle()
    val currentState = uiState.value
    val loginValueUiState = viewModel.loginValueUiState.collectAsStateWithLifecycle()
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.NavigateEvent -> {
                   navigateToHome()
                }
                is UiEvents.ToastEvent ->{
                    Toast.makeText(context,event.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (currentState) {
            is LoginUiState.Initial -> {
                LoginScreenContent(
                    email = loginValueUiState.value.email,
                    password = loginValueUiState.value.password,
                    onEmailValueChanged = {
                        viewModel.onEmailChange(it)
                    },
                    onPasswordValueChanged = {
                        viewModel.onPasswordChange(it)
                    },
                    onEnterButtonClick = {
                        val loginBody = RegistrationBody(
                            email = loginValueUiState.value.email,
                            password = loginValueUiState.value.password
                        )
                        viewModel.login(loginBody)
                    },
                    navigation = navigateToRegister
                )
            }

            is LoginUiState.Loading -> {
                ProgressBlock()
            }

            is LoginUiState.Success -> {

            }

            is LoginUiState.Error -> {
                LoginScreenContent(
                    email = loginValueUiState.value.email,
                    password = loginValueUiState.value.password,
                    onEmailValueChanged = {
                        viewModel.onEmailChange(it)
                    },
                    onPasswordValueChanged = {
                        viewModel.onPasswordChange(it)
                    },
                    onEnterButtonClick = {
                        val loginBody = RegistrationBody(
                            email = loginValueUiState.value.email,
                            password = loginValueUiState.value.password
                        )
                        viewModel.login(loginBody)
                    },
                    navigation = navigateToRegister
                )
            }
        }
    }
}

@Composable
fun LoginScreenContent(
    email: String,
    password: String,
    onEmailValueChanged: (String) -> Unit,
    onPasswordValueChanged: (String) -> Unit,
    onEnterButtonClick:() ->Unit,
    navigation:() ->Unit
) {

    var showPasswordValue by rememberSaveable { mutableStateOf(value = false) }
    var confirmShowPasswordValue by rememberSaveable { mutableStateOf(value = false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        AuthScreenTopBlock(
            onBackIconClick = { /*TODO*/ },
            mainText = stringResource(id = R.string.hello),
            subText = stringResource(id = R.string.enter_to_account)
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
        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            text = stringResource(id = R.string.enter),
            onButtonClick = onEnterButtonClick
        )
        Spacer(modifier = Modifier.height(16.dp))
        SpannableNavigateText(
            subText = stringResource(id = R.string.dont_have_account),
            navigation = navigation,
            annotatedString = stringResource(id = R.string.follow)
        )
    }
}


