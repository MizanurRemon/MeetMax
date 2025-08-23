package com.meetmax.auth_presentaion.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.meetmax.designsystem.theme.BACKGROUND_COLOR


@Composable
fun SignUpScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_COLOR)
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Sign Up")
    }
}

@Composable
fun PreviewSignUpScreen(){
    SignUpScreen()
}