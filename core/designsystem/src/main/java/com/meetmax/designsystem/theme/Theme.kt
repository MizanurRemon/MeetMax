package com.meetmax.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.meetmax.designsystem.ssp

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MeetMaxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val heading1TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 30.ssp(),
    color = ColorTextPrimary,
    fontWeight = FontWeight.W900,
    textAlign = TextAlign.Center,
    lineHeight = 46.ssp(),
    letterSpacing = 0.ssp()
)


val heading2TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 26.ssp(),
    color = ColorTextPrimary,
    fontWeight = FontWeight.W700,
    textAlign = TextAlign.Center,
    lineHeight = 40.ssp(),
    letterSpacing = 0.ssp()
)

val heading3TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 18.ssp(),
    color = Color(0xFF4E5D78),
    fontWeight = FontWeight.W700,
    textAlign = TextAlign.Center,
    lineHeight = 28.ssp(),
    letterSpacing = 0.ssp()
)

val displayBoldTextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 16.ssp(),
    color = Color(0xFF4E5D78),
    fontWeight = FontWeight.W700,
    textAlign = TextAlign.Center,
    lineHeight = 24.ssp(),
    letterSpacing = 0.ssp()
)

val displayMediumTextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 16.ssp(),
    color = Color(0xFF4E5D78),
    fontWeight = FontWeight.W500,
    textAlign = TextAlign.Center,
    lineHeight = 24.ssp(),
    letterSpacing = 0.ssp()
)

val bodyBoldTextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 14.ssp(),
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W700,
    textAlign = TextAlign.Center,
    lineHeight = 22.ssp(),
    letterSpacing = 0.ssp()
)

val bodyMedium1TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 14.ssp(),
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W500,
    textAlign = TextAlign.Center,
    lineHeight = 22.ssp(),
    letterSpacing = 0.ssp()
)

val bodyMedium2TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 13.ssp(),
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W500,
    textAlign = TextAlign.Center,
    lineHeight = 18.ssp(),
    letterSpacing = 0.ssp()
)

val bodyMedium3TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 12.ssp(),
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W500,
    textAlign = TextAlign.Center,
    lineHeight = 18.ssp(),
    letterSpacing = 0.ssp()
)

val bodyRegularTextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 14.ssp(),
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W400,
    textAlign = TextAlign.Center,
    lineHeight = 22.ssp(),
    letterSpacing = 0.ssp()
)

val bodyRegularM3TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 12.ssp(),
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W400,
    textAlign = TextAlign.Center,
    lineHeight = 18.ssp(),
    letterSpacing = 0.ssp()
)

val bodyRegularM4TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 10.ssp(),
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W500,
    textAlign = TextAlign.Center,
    lineHeight = 16.ssp(),
    letterSpacing = 0.ssp()
)

val bodyRegular4TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 10.ssp(),
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W400,
    textAlign = TextAlign.Center,
    lineHeight = 16.ssp(),
    letterSpacing = 0.ssp()
)