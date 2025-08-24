package com.meetmax.auth_presentaion.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meetmax.designsystem.components.AppActionButton
import com.meetmax.designsystem.components.AuthTopBar
import com.meetmax.designsystem.components.CommonTextField
import com.meetmax.designsystem.components.OrDividerComponent
import com.meetmax.designsystem.components.PasswordTextField
import com.meetmax.designsystem.rippleClickable
import com.meetmax.designsystem.theme.BACKGROUND_COLOR
import com.meetmax.designsystem.theme.appBrush
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.bodyMedium3TextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.heading3TextStyle
import com.meetmax.designsystem.theme.primaryBlue
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onForgotPassword: () -> Unit,
    onSignUp: () -> Unit,
    onSignIn: () -> Unit
) {

    val annotateSignUpString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = grayScale)) {
            append(stringResource(id = CommonR.string.you_have_not_any_account) + " ")
        }

        pushStringAnnotation(
            tag = CommonR.string.sign_up.toString(),
            annotation = CommonR.string.sign_up.toString()
        )

        withStyle(style = SpanStyle(color = primaryBlue)) {
            append(stringResource(id = CommonR.string.sign_up))
        }

        append(".")
    }

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

        Text(text = stringResource(CommonR.string.sign_in), style = heading3TextStyle)

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            stringResource(CommonR.string.welcome_back_you_have_been_missed),
            style = bodyMedium1TextStyle.copy(color = grayScale)
        )

        Spacer(modifier = Modifier.height(30.dp))

        ContentBox(
            state = state,
            onEvent = onEvent,
            onForgotPassword = {
                onForgotPassword()
            },
            onSignUp = {
                onSignUp()
            },
            annotateSignUpString = annotateSignUpString,
            onSignIn = {
                onSignIn()
            }
        )
    }
}

@Composable
fun ContentBox(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onForgotPassword: () -> Unit,
    onSignUp: () -> Unit,
    annotateSignUpString: AnnotatedString,
    onSignIn: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = appBrush,
                shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp)
            ),

        ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
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

                Spacer(modifier = Modifier.width(13.dp))

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

            OrDividerComponent(
                modifier = Modifier.padding(top = 26.dp, bottom = 24.dp)
            )

            CommonTextField(
                value = state.email,
                onValueChange = { onEvent(LoginEvent.OnEmailEnter(it)) },
                isTouched = state.isEmailTouched,
                isValid = state.isMailValid,
                onTouched = { onEvent(LoginEvent.OnEmailTouchedListener) },
                placeholder = stringResource(id = CommonR.string.your_email),
                leadingIcon = painterResource(id = DesignSystemR.drawable.ic_mail),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )



            Spacer(modifier = Modifier.height(14.dp))

            PasswordTextField(
                value = state.password,
                onValueChange = { onEvent(LoginEvent.OnPasswordEnter(it)) },
                isTouched = state.isPasswordTouched,
                isValid = state.isPasswordValid,
                onTouched = { onEvent(LoginEvent.OnPasswordTouchedListener) },
                keyboardController = keyboardController
            )

//            Spacer(modifier = Modifier.height(14.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {

                Checkbox(
                    modifier = Modifier.size(45.dp),
                    checked = state.isRememberMeChecked,
                    onCheckedChange = {
                        onEvent(LoginEvent.OnRememberMeChecked(it))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = primaryBlue,
                        uncheckedColor = grayScale,
                        checkmarkColor = Color.White
                    )
                )

                Text(
                    text = stringResource(CommonR.string.remember_me),
                    style = bodyMedium1TextStyle.copy(
                        textAlign = TextAlign.Start,
                        color = grayScale
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = stringResource(CommonR.string.forgot_password),
                    style = bodyMedium1TextStyle.copy(
                        textAlign = TextAlign.Start,
                        color = grayScale
                    ),
                    modifier = Modifier.rippleClickable {
                        onForgotPassword()
                    }
                )
            }



            Spacer(modifier = Modifier.height(20.dp))

            AppActionButton(
                text = CommonR.string.sign_in,
                bgColor = primaryBlue,
                onClick = {
                    onSignIn()
                },
                textStyle = bodyMedium1TextStyle.copy(color = Color.White),
                radius = 6,
                modifier = Modifier.height(40.dp).fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier
                    .clickable {
                        annotateSignUpString
                            .getStringAnnotations(
                                tag = CommonR.string.sign_up.toString(),
                                start = 0,
                                end = annotateSignUpString.length
                            )
                            .firstOrNull()
                            ?.let {
                                onSignUp()
                            }
                    },
                text = annotateSignUpString,
                style = bodyMedium3TextStyle
            )

        }
    }
}


@Composable
@Preview
fun PreviewLoginScreen() {
    LoginScreen(
        state = LoginState(
            isRememberMeChecked = false
        ),
        onEvent = {},
        onForgotPassword = {},
        onSignUp = {},
        onSignIn = {}
    )
}