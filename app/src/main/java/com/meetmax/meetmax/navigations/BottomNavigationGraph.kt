package com.meetmax.meetmax.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.meetmax.designsystem.BottomNavRoute
import com.meetmax.meetmax.home.bottom_nav_screens.CommunityScreen
import com.meetmax.meetmax.home.bottom_nav_screens.ExploreScreen
import com.meetmax.feed_presentation.FeedScreen
import com.meetmax.meetmax.home.bottom_nav_screens.NotificationScreen
import com.meetmax.meetmax.home.bottom_nav_screens.SettingsScreen

@Composable
fun BottomNavigationGraph(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = BottomNavRoute.FEED
    ) {

        composable (BottomNavRoute.FEED) {
            FeedScreen()
        }

        composable(BottomNavRoute.MY_COMMUNITY) {
            CommunityScreen()
        }

        composable(BottomNavRoute.EXPLORE) {
            ExploreScreen()
        }

        composable(BottomNavRoute.NOTIFICATION) {
            NotificationScreen()
        }

        composable(BottomNavRoute.SETTING) {
            SettingsScreen()
        }

    }
}