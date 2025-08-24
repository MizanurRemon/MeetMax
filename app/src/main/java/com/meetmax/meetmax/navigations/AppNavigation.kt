package com.meetmax.meetmax.navigations

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.meetmax.auth_presentaion.forgot_password.ForgotPasswordScreen
import com.meetmax.auth_presentaion.forgot_password.ForgotPasswordViewModel
import com.meetmax.auth_presentaion.login.LoginScreen
import com.meetmax.auth_presentaion.login.LoginViewModel
import com.meetmax.auth_presentaion.registration.SignUpScreen
import com.meetmax.auth_presentaion.registration.SignUpViewModel
import com.meetmax.common.navigation.Route
import com.meetmax.meetmax.home.HomeScreen
import com.meetmax.meetmax.splash.SplashScreen
import com.meetmax.meetmax.splash.SplashViewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.SPLASH,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Route.SPLASH) {
                val viewModel = hiltViewModel<SplashViewModel>()
                SplashScreen(
                    uiEvent = viewModel.uiEvent,
                    onLogin = {
                        navController.navigate(Route.LOGIN) {
                            popUpTo(navController.graph.id) {}
                        }
                    }
                )
            }

            composable(route = Route.LOGIN) {
                val viewModel = hiltViewModel<LoginViewModel>()
                LoginScreen(
                    onEvent = viewModel::onEvent,
                    state = viewModel.state,
                    onForgotPassword = {
                        navController.navigate(Route.FORGOT_PASSWORD)
                    },
                    onSignUp = {
                        navController.navigate(Route.SIGN_UP)
                    },
                    onSignIn = {
                        navController.navigate(Route.HOME)
                    },
                    launchSignInIntentFlow = viewModel.launchSignInIntent
                )
            }

            composable(route = Route.FORGOT_PASSWORD) {
                val viewModel = hiltViewModel<ForgotPasswordViewModel>()
                ForgotPasswordScreen(
                    onEvent = viewModel::onEvent,
                    state = viewModel.state,
                    onBack = {
                        navController.navigateUp()
                    }
                )
            }

            composable(route = Route.SIGN_UP) {
                val viewModel = hiltViewModel<SignUpViewModel>()
                SignUpScreen(
                    state = viewModel.state,
                    onEvent = viewModel::onEvent,
                    onSignIn = {
                        navController.navigateUp()
                    }
                )
            }

            composable(route = Route.HOME) {
                HomeScreen(
                )
            }
        }
    }

}