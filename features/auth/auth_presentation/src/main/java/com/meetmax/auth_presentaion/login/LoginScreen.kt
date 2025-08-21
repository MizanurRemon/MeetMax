package com.meetmax.auth_presentaion.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.meetmax.designsystem.r
import com.meetmax.designsystem.theme.BACKGROUND_COLOR
import com.meetmax.ui.DevicePreviews

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_COLOR)
            .padding(horizontal = 35.r())
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login")
    }
}


@Composable
@DevicePreviews
fun PreviewLoginScreen() {
    LoginScreen()
}