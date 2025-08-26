package com.meetmax.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.meetmax.designsystem.theme.bodyRegularM3TextStyle

@Composable
fun OverlappingAvatars(
    imageUrls: List<Int>,
    overlap: Int,
    size: Int
) {
    val rightOffset =
        if (imageUrls.size > 3) (overlap * (-3)) else (overlap * (imageUrls.size)) + (size + overlap)

    Row(
        modifier = Modifier.offset(x = rightOffset.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        imageUrls.take(3).forEachIndexed { index, imageUrl ->
            Box(
                modifier = Modifier
                    .offset(x = (overlap * index).dp)
            ) {
                DrawableCircleImage(
                    imageUrl = imageUrl,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(size.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.White, CircleShape)
                        .zIndex((imageUrls.size - index).toFloat())
                )
            }
        }

        if (imageUrls.size > 3) {
            Box(
                modifier = Modifier
                    .offset(x = overlap.dp * imageUrls.take(3).size)
                    .size(size.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+${imageUrls.size - 3}",
                    style = bodyRegularM3TextStyle.copy(
                        color = Color.White,
                        fontSize = 10.sp
                    )
                )
            }
        }
    }
}