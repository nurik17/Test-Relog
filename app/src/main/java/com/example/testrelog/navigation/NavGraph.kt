package com.example.testrelog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testrelog.presentation.home.HomeScreen
import com.example.testrelog.presentation.login.LoginScreen
import com.example.testrelog.presentation.register.RegisterScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = AppNavigation.Screen.Login.route
    ){
        composable(route = AppNavigation.Screen.Login.route) {
            LoginScreen(
                navigateToHome = {
                    navController.navigate(AppNavigation.Screen.Home.route)
                },
                navigateToRegister = {
                    navController.navigate(AppNavigation.Screen.Register.route)
                }
            )
        }
        composable(route = AppNavigation.Screen.Register.route){
            RegisterScreen(
                navigateToLogin = {
                    navController.navigate(AppNavigation.Screen.Login.route)
                }
            )
        }
        composable(route = AppNavigation.Screen.Home.route){
            HomeScreen()
        }
    }
}



object AppNavigation {
    const val LOGIN_ROUTE = "login"
    const val REGISTER_ROUTE = "register"
    const val HOME_ROUTE = "home"

    sealed class Screen(val route: String) {
        object Login : Screen(LOGIN_ROUTE)
        object Register : Screen(REGISTER_ROUTE)
        object Home : Screen(HOME_ROUTE)
    }
}