package com.meetmax.meetmax.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.meetmax.designsystem.components.TopAppBar
import com.meetmax.designsystem.theme.BACKGROUND_COLOR

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(remember { mutableStateOf("") })
        },
        bottomBar = {

        },
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_COLOR)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) { }
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    HomeScreen()
}