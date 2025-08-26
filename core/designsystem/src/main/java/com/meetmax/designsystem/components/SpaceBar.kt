package com.meetmax.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.meetmax.designsystem.theme.grayScale

@Composable
fun SpaceBar(
    height: Int = 1,
    color: Color = grayScale.copy(alpha = .2f)
) {
    Spacer(
        modifier = Modifier
            .height(10.dp)

    )

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .background(color = color)
    )

    Spacer(
        modifier = Modifier
            .height(10.dp)

    )
}
