package com.meetmax.designsystem.components

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.runtime.saveable.Saver



@Composable
fun DrawableCircleUriImage(
    imageUrl: Uri,
    size: Int = 50,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp,
    contentScale: ContentScale = ContentScale.Crop,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
            .size(size.dp)
            .clip(shape)
            .border(BorderStroke(borderWidth, borderColor), shape)
    )
}

@Composable
fun DrawableCircleImage(
    imageUrl: Int,
    size: Int = 50,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp,
    contentScale: ContentScale = ContentScale.Crop,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
            .size(size.dp)
            .clip(shape)
            .border(BorderStroke(borderWidth, borderColor), shape)
    )
}

@Composable
fun DrawableCircleImageUrl(
    imageUrl: String,
    size: Int = 50,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp,
    contentScale: ContentScale = ContentScale.Crop,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
            .size(size.dp)
            .clip(shape)
            .border(BorderStroke(borderWidth, borderColor), shape)
    )
}