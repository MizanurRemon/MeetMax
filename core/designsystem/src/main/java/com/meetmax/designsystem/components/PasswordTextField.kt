package com.meetmax.designsystem.components


import android.util.Patterns
import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meetmax.designsystem.theme.ColorError
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.primaryBlue
import com.meetmax.designsystem.R as DesignSystemR
import com.meetmax.common.R as CommonR

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isTouched: Boolean,
    isValid: Boolean,
    onTouched: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(id = CommonR.string.create_password),
    keyboardController: SoftwareKeyboardController? = null
) {
    var showPassword by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(6.dp),
                color = if (isTouched) {
                    if (isValid) primaryBlue else ColorError
                } else {
                    grayScale.copy(alpha = 0.2f)
                }
            )
            .background(Color.White, RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp)
            .onFocusEvent { event ->
                if (event.isFocused) onTouched()
            }
            .pointerInteropFilter {
                if (it.action == MotionEvent.ACTION_DOWN) {
                    onTouched()
                }
                false
            }
    ) {
        Image(
            painter = painterResource(id = DesignSystemR.drawable.ic_lock),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = bodyMedium1TextStyle.copy(
                color = Color.Black,
                textAlign = TextAlign.Start
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                defaultKeyboardAction(ImeAction.Done)
            }),
            cursorBrush = SolidColor(primaryBlue),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 15.dp),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = bodyMedium1TextStyle.copy(
                            color = grayScale.copy(alpha = 0.6f),
                            textAlign = TextAlign.Start
                        )
                    )
                }
                innerTextField()
            }
        )

        Icon(
            painter = painterResource(
                id = if (showPassword) {
                    DesignSystemR.drawable.ic_eye_close
                } else {
                    DesignSystemR.drawable.ic_eye_open
                }
            ),
            contentDescription = null,
            tint = grayScale,
            modifier = Modifier
                .clickable { showPassword = !showPassword }
                .size(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPasswordTextField() {
    var password by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    PasswordTextField(
        value = password,
        onValueChange = { password = it },
        isTouched = password.isNotEmpty(),
        isValid = password.length >= 6,
        onTouched = { /* Preview: no-op */ },
        keyboardController = keyboardController
    )
}

