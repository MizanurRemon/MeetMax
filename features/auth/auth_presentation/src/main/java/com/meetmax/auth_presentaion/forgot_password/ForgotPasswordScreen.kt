package com.meetmax.auth_presentaion.forgot_password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meetmax.designsystem.components.AppActionButton
import com.meetmax.designsystem.components.AuthTopBar
import com.meetmax.designsystem.components.EmailTextField
import com.meetmax.designsystem.rippleClickable
import com.meetmax.designsystem.theme.BACKGROUND_COLOR
import com.meetmax.designsystem.theme.appBrush
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.displayMediumTextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.heading3TextStyle
import com.meetmax.designsystem.theme.primaryBlue
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun ForgotPasswordScreen(
    state: ForgotPasswordState,
    onEvent: (ForgotPasswordEvent) -> Unit,
    onBack:()-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_COLOR)
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthTopBar(
            onLanguageClick = {

            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = stringResource(CommonR.string.forgot_password), style = heading3TextStyle)

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(CommonR.string.enter_your_details_to_receive_a_rest_link),
            style = bodyMedium1TextStyle.copy(
                color = grayScale
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    brush = appBrush,
                    shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp)
                )
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailTextField(
                    value = state.email,
                    onValueChange = { onEvent(ForgotPasswordEvent.OnEmailEnter(it)) },
                    isTouched = state.isEmailTouched,
                    isValid = state.isMailValid,
                    onTouched = { onEvent(ForgotPasswordEvent.OnEmailTouchedListener) },
                    placeholder = stringResource(id = CommonR.string.your_email),
                    leadingIcon = painterResource(id = DesignSystemR.drawable.ic_mail),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.height(14.dp))
                AppActionButton(
                    onClick = {

                    },
                    text = CommonR.string.send,
                    bgColor = primaryBlue,
                    radius = 6,
                    textStyle = bodyMedium1TextStyle.copy(
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = stringResource(CommonR.string.back_to_sign_in),
                    style = displayMediumTextStyle.copy(
                        color = primaryBlue
                    ),
                    modifier = Modifier.rippleClickable {
                        onBack()
                    }
                )

            }
        }
    }
}


@Composable
@Preview
fun PreviewForgotPasswordScreen() {
    ForgotPasswordScreen(
        state = ForgotPasswordState(),
        onEvent = {},
        onBack = {}
    )
}