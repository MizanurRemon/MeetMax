package com.meetmax.meetmax.navigations

import android.util.Log
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
import com.meetmax.common.navigation.Route
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
                       //
                       Log.d("dataxx", "AppNavigation: ")
                   }
               )
            }
        }
    }

}