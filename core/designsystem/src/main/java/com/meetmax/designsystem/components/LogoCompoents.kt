package com.meetmax.designsystem.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.meetmax.designsystem.r
import com.meetmax.designsystem.theme.BACKGROUND_COLOR
import com.meetmax.designsystem.theme.displayMediumTextStyle
import com.meetmax.designsystem.theme.heading3TextStyle
import com.meetmax.designsystem.R as DesignSystemR
import com.meetmax.common.R as CommonR

@Composable
fun LogoWithTextVertical(
    iconSize: Int,
    @StringRes title: Int,
    textStyle: TextStyle
) {
    Card(
        shape = RoundedCornerShape(10.r()),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier.shadow(
            elevation = 10.r(),
            spotColor = Color.White,
            shape = RoundedCornerShape(10.r())
        )
    ) {
        Row(modifier = Modifier.padding(horizontal = 17.r(), vertical = 16.r())) {
            Image(
                painter = painterResource(DesignSystemR.drawable.ic_logo),
                modifier = Modifier.size(iconSize.r()),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(10.r()))

            Text(
                text = stringResource(title),
                style = textStyle
            )
        }
    }
}

@Composable
@Preview
fun PreviewLogoWithTextVertical() {
    Box(
        modifier = Modifier
            .background(color = BACKGROUND_COLOR)
    ) {
        Column(
            modifier = Modifier.padding(10.r())
        ) {
            LogoWithTextVertical(
                iconSize = 26,
                title = CommonR.string.meetmax,
                textStyle = heading3TextStyle
            )

            Spacer(modifier = Modifier.height(10.r()))

            LogoWithTextVertical(
                iconSize = 20,
                title = CommonR.string.meetmax_call,
                textStyle = displayMediumTextStyle
            )
        }
    }
}