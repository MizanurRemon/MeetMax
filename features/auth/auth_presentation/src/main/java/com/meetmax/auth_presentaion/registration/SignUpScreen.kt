package com.meetmax.auth_presentaion.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.unit.dp
import com.meetmax.common.util.GENDER
import com.meetmax.designsystem.components.AppActionButton
import com.meetmax.designsystem.components.AuthTopBar
import com.meetmax.designsystem.components.CommonTextField
import com.meetmax.designsystem.components.OrDividerComponent
import com.meetmax.designsystem.components.PasswordTextField
import com.meetmax.designsystem.dialogs.MyDatePickerDialog
import com.meetmax.designsystem.theme.BACKGROUND_COLOR
import com.meetmax.designsystem.theme.appBrush
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.bodyMedium3TextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.heading3TextStyle
import com.meetmax.designsystem.theme.primaryBlue
import com.meetmax.ui.DevicePreviews
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR


@Composable
fun SignUpScreen(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit,
    onSignIn: () -> Unit
) {

    val annotateSignUpString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = grayScale)) {
            append(stringResource(id = CommonR.string.already_have_an_account) + " ")
        }

        pushStringAnnotation(
            tag = CommonR.string.sign_in.toString(),
            annotation = CommonR.string.sign_in.toString()
        )

        withStyle(style = SpanStyle(color = primaryBlue)) {
            append(stringResource(id = CommonR.string.sign_in))
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

        Text(text = stringResource(CommonR.string.getting_started), style = heading3TextStyle)

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            stringResource(CommonR.string.create_an_account_to_continue_and_connect_with_the_people),
            style = bodyMedium1TextStyle.copy(color = grayScale),
            modifier = Modifier.padding(horizontal = 23.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        ContentBox(
            state = state,
            onEvent = onEvent,
            onSignIn = {
                onSignIn()
            },
            annotateSignUpString = annotateSignUpString
        )
    }
}

@Composable
private fun ContentBox(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit,
    onSignIn: () -> Unit,
    annotateSignUpString: AnnotatedString
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
                onValueChange = { onEvent(SignUpEvent.OnEmailEnter(it)) },
                isTouched = state.isEmailTouched,
                isValid = state.isMailValid,
                onTouched = { onEvent(SignUpEvent.OnEmailTouchedListener) },
                placeholder = stringResource(id = CommonR.string.your_email),
                leadingIcon = painterResource(id = DesignSystemR.drawable.ic_mail),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(14.dp))

            CommonTextField(
                value = state.name,
                onValueChange = {
                    onEvent(SignUpEvent.OnNameInput(it))
                },
                isTouched = state.isNameTouched,
                isValid = true,
                onTouched = { onEvent(SignUpEvent.OnNameInputTouchedListener) },
                placeholder = stringResource(id = CommonR.string.your_name),
                leadingIcon = painterResource(id = DesignSystemR.drawable.ic_smile),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(14.dp))

            PasswordTextField(
                value = state.password,
                onValueChange = { onEvent(SignUpEvent.OnPasswordEnter(it)) },
                isTouched = state.isPasswordTouched,
                isValid = state.isPasswordValid,
                onTouched = { onEvent(SignUpEvent.OnPasswordTouchedListener) },
                keyboardController = keyboardController
            )

            Spacer(modifier = Modifier.height(14.dp))

            CommonTextField(
                readOnly = true,
                value = state.dob,
                onValueChange = {
                    //onEvent(SignUpEvent.OnDateEnter(it))
                },
                isTouched = false,
                isValid = true,
                onTouched = { onEvent(SignUpEvent.OnDateSelection(true)) },
                placeholder = stringResource(id = CommonR.string.date_of_birth),
                leadingIcon = painterResource(id = DesignSystemR.drawable.ic_calendar),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = bodyMedium1TextStyle.copy(textAlign = TextAlign.Start)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(6.dp),
                        color = grayScale.copy(.2f)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(DesignSystemR.drawable.ic_gender),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )

                    GENDER.forEach { option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                //.fillMaxWidth()
                                // .clickable {  }
                                .padding(vertical = 8.dp, horizontal = 12.dp)
                        ) {
                            RadioButton(
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = primaryBlue,
                                    unselectedColor = grayScale
                                ),
                                modifier = Modifier.size(16.dp),
                                selected = (option == state.gender),
                                onClick = { onEvent(SignUpEvent.OnOptionSelected(option)) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(option),
                                style = bodyMedium1TextStyle.copy(
                                    color = if (option == state.gender) grayScale else grayScale.copy(
                                        alpha = .6f
                                    )
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            AppActionButton(
                text = CommonR.string.sign_up,
                bgColor = primaryBlue,
                onClick = {

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
                                tag = CommonR.string.sign_in.toString(),
                                start = 0,
                                end = annotateSignUpString.length
                            )
                            .firstOrNull()
                            ?.let {
                                onSignIn()
                            }
                    },
                text = annotateSignUpString,
                style = bodyMedium3TextStyle
            )

        }
    }

    if (state.isDatePickerOpened) {
        MyDatePickerDialog(
            onDateSelected = {
                onEvent(SignUpEvent.OnDateEnter(it))
            },
            onDismiss = {
                onEvent(SignUpEvent.OnDateSelection(false))
            }
        )
    }
}

@Composable
@DevicePreviews
fun PreviewSignUpScreen() {
    SignUpScreen(
        state = SignUpState(),
        onEvent = {},
        onSignIn = {}
    )
}