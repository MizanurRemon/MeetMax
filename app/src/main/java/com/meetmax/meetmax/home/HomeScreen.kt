package com.meetmax.meetmax.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.meetmax.designsystem.BOTTOM_NAVIGATION_ITEM
import com.meetmax.designsystem.components.CustomNavigationItem
import com.meetmax.designsystem.components.TopAppBar
import com.meetmax.designsystem.theme.BACKGROUND_COLOR
import com.meetmax.meetmax.home.bottom_nav_screens.HomeState
import com.meetmax.meetmax.navigations.BottomNavigationGraph

@Composable
fun HomeScreen(
    state: HomeState
) {
    val bottomNavController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(search = remember { mutableStateOf("") }, photo = state.photo)
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentDestination: NavDestination? = navBackStackEntry?.destination
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BOTTOM_NAVIGATION_ITEM.forEach { item ->

                        CustomNavigationItem(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                bottomNavController.navigate(item.route) {
                                    popUpTo(bottomNavController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            title = item.title,
                            icon = item.icon,
                            isSelected = currentDestination?.route == item.route
                        )

                    }

                }
            }
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = BACKGROUND_COLOR
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 8.dp)
        ) {
            BottomNavigationGraph(bottomNavController)
        }
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {

    HomeScreen(
        state = HomeState()
    )
}