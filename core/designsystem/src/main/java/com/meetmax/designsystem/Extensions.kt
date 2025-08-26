package com.meetmax.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed


fun Modifier.rippleClickable(
    onClick: () -> Unit
): Modifier = composed {
    this.clickable(
        onClick = onClick,
        interactionSource = remember { MutableInteractionSource() },
        indication = ripple()
    )
}

fun String.capitalizeFirstChar(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}