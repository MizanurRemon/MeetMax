package com.meetmax.auth_presentaion.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.meetmax.designsystem.components.AppActionButton
import com.meetmax.designsystem.components.AuthTopBar
import com.meetmax.designsystem.r
import com.meetmax.designsystem.theme.BACKGROUND_COLOR
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.bodyMedium3TextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.heading3TextStyle
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_COLOR)
            .padding(24.r())
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthTopBar(
            onLanguageClick = {

            }
        )

        Spacer(modifier = Modifier.height(40.r()))

        Text(text = stringResource(CommonR.string.sign_in), style = heading3TextStyle)

        Spacer(modifier = Modifier.height(10.r()))

        Text(
            stringResource(CommonR.string.welcome_back_you_have_been_missed),
            style = bodyMedium1TextStyle.copy(color = grayScale)
        )

        Spacer(modifier = Modifier.height(30.r()))

        ContentBox()
    }
}

@Composable
fun ContentBox() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = BACKGROUND_COLOR
        ),
        elevation = CardDefaults.cardElevation(1.r())
    ) {
        Column(modifier = Modifier.padding(20.r())) {
            Row {
                AppActionButton(
                    icon = DesignSystemR.drawable.ic_google,
                    text = CommonR.string.log_in_with_google,
                    bgColor = grayScale.copy(alpha = .05f),
                    onClick = {},
                    modifier = Modifier
                        .weight(1f),
                    radius = 6,
                    textStyle = bodyMedium3TextStyle.copy(
                        color = grayScale
                    )
                )

                Spacer(modifier = Modifier.width(13.r()))

                AppActionButton(
                    icon = DesignSystemR.drawable.ic_google,
                    text = CommonR.string.log_in_with_google,
                    bgColor = grayScale.copy(alpha = .05f),
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    radius = 6,
                    textStyle = bodyMedium3TextStyle.copy(
                        color = grayScale
                    )
                )
            }
        }
    }
}


@Composable
@Preview
fun PreviewLoginScreen() {
    LoginScreen()
}