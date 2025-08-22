package com.meetmax.designsystem.components

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meetmax.designsystem.r
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.bodyMedium3TextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.primaryBlue
import com.meetmax.designsystem.R as DesignSystemR
import com.meetmax.common.R as CommonR

@Composable
fun AppActionButton(
    @DrawableRes icon: Int? = null,
    @StringRes text: Int,
    bgColor: Color = primaryBlue,
    onClick: () -> Unit,
    textStyle: TextStyle = bodyMedium1TextStyle,
    radius: Int = 6,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = bgColor, shape = RoundedCornerShape(radius))
            .padding(10.dp)
            .clickable {
                onClick()
            }
            .clip(RoundedCornerShape(radius.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            icon?.let {
                Image(
                    contentDescription = null,
                    painter = painterResource(icon)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = stringResource(text),
                style = textStyle
            )
        }
    }
}

@Composable
@Preview
fun PreviewAppActionButton() {
    AppActionButton(
        icon = DesignSystemR.drawable.ic_google,
        text = CommonR.string.log_in_with_google,
        bgColor = grayScale.copy(0.05f),
        onClick = {},
        textStyle = bodyMedium3TextStyle.copy(
            color = grayScale
        ),
        radius = 16
    )
}