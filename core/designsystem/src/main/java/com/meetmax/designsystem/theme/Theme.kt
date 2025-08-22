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
import androidx.compose.ui.unit.sp
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
    fontSize = 30.sp,
    color = grayScale,
    fontWeight = FontWeight.W900,
    textAlign = TextAlign.Center,
    lineHeight = 46.sp,
    letterSpacing = 0.sp
)


val heading2TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 26.sp,
    color = grayScale,
    fontWeight = FontWeight.W700,
    textAlign = TextAlign.Center,
    lineHeight = 40.sp,
    letterSpacing = 0.sp
)

val heading3TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 18.sp,
    color = grayScale,
    fontWeight = FontWeight.W700,
    textAlign = TextAlign.Center,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
)

val displayBoldTextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 16.sp,
    color = grayScale,
    fontWeight = FontWeight.W700,
    textAlign = TextAlign.Center,
    lineHeight = 24.sp,
    letterSpacing = 0.sp
)

val displayMediumTextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 16.sp,
    color = grayScale,
    fontWeight = FontWeight.W500,
    textAlign = TextAlign.Center,
    lineHeight = 24.sp,
    letterSpacing = 0.sp
)

val bodyBoldTextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 14.sp,
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W700,
    textAlign = TextAlign.Center,
    lineHeight = 22.sp,
    letterSpacing = 0.sp
)

val bodyMedium1TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 14.sp,
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W500,
    textAlign = TextAlign.Center,
    lineHeight = 22.sp,
    letterSpacing = 0.sp
)

val bodyMedium2TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 13.sp,
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W500,
    textAlign = TextAlign.Center,
    lineHeight = 18.sp,
    letterSpacing = 0.sp
)

val bodyMedium3TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 12.sp,
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W500,
    textAlign = TextAlign.Center,
    lineHeight = 18.sp,
    letterSpacing = 0.sp
)

val bodyRegularTextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 14.sp,
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W400,
    textAlign = TextAlign.Center,
    lineHeight = 22.sp,
    letterSpacing = 0.sp
)

val bodyRegularM3TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 12.sp,
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W400,
    textAlign = TextAlign.Center,
    lineHeight = 18.sp,
    letterSpacing = 0.sp
)

val bodyRegularM4TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 10.sp,
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W500,
    textAlign = TextAlign.Center,
    lineHeight = 16.sp,
    letterSpacing = 0.sp
)

val bodyRegular4TextStyle = TextStyle(
    fontFamily = fontRoboto,
    fontSize = 10.sp,
    color = Color(0xFFB0B7C3),
    fontWeight = FontWeight.W400,
    textAlign = TextAlign.Center,
    lineHeight = 16.sp,
    letterSpacing = 0.sp
)