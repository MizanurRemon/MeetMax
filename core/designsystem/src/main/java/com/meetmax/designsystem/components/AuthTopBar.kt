package com.meetmax.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.meetmax.designsystem.r
import com.meetmax.designsystem.theme.BACKGROUND_COLOR
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.theme.bodyMedium3TextStyle
import com.meetmax.designsystem.theme.bodyRegularM4TextStyle
import com.meetmax.designsystem.theme.bodyRegularTextStyle
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun AuthTopBar(onLanguageClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = BACKGROUND_COLOR),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LogoWithTextVerticalPlain(
            iconSize = 16,
            title = CommonR.string.meetmax,
            textStyle = bodyMedium3TextStyle.copy(
                color = Color(0xFF4E5D78),
                fontWeight = FontWeight.W700
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(4.r()),
            modifier = Modifier.shadow(
                elevation = 5.r(),
                spotColor = Color.LightGray,
                shape = RoundedCornerShape(4.r())
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.r(), horizontal = 10.r())
            ) {
                Text(
                    text = "English (UK)", style = bodyRegularM4TextStyle
                )

                Spacer(modifier = Modifier.width(7.r()))

                Image(
                    painter = painterResource(DesignSystemR.drawable.ic_angle_down),
                    modifier = Modifier.size(12.r()),
                    contentDescription = null
                )
            }
        }

    }
}


@Composable
@Preview
fun PreviewAuthTopBar() {
    AuthTopBar(
        onLanguageClick = {}
    )
}