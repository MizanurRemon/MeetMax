package com.meetmax.meetmax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.meetmax.designsystem.theme.MeetMaxTheme
import com.meetmax.meetmax.navigations.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeetMaxTheme {
                val navController: NavHostController = rememberNavController()
                AppNavigation(
                    navController = navController
                )
            }
        }
    }
}
