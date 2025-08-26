package com.meetmax.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundImageWithIconCompose(
    primaryColor: Color,
    secondaryColor: Color,
    @DrawableRes icon: Int
) {
    Box(
        modifier = Modifier
            .background(
                color = primaryColor,
                shape = RoundedCornerShape(8.dp)
            )

    ) {
        Box(
            modifier = Modifier
                .background(
                    color = secondaryColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
                    .align(Alignment.Center)
            )
        }
    }
}