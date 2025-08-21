package com.meetmax.meetmax.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.meetmax.common.util.UiEvent
import com.meetmax.designsystem.r
import com.meetmax.designsystem.theme.BACKGROUND_COLOR
import com.meetmax.designsystem.theme.heading3TextStyle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun SplashScreen(
    uiEvent: Flow<UiEvent>,
    onLogin: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->

            when (event) {
                is UiEvent.Success -> {

                }

                is UiEvent.ShowSnackbar -> {
                }

                is UiEvent.NavigateUp -> {
                    onLogin()
                }
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BACKGROUND_COLOR),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(
                DesignSystemR.drawable.ic_logo
            ),
            contentDescription = null,
            modifier = Modifier.size(100.r())
        )

        Spacer(modifier = Modifier.height(10.r()))
        Text(
            text = stringResource(CommonR.string.app_name),
            style = heading3TextStyle
        )


    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen(
        uiEvent = flow { },
        onLogin = {}
    )
}