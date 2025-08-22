package com.meetmax.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.meetmax.designsystem.r
import com.meetmax.designsystem.theme.bodyRegularM4TextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.common.R as CommonR

@Composable
fun OrDividerComponent(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (startLine, orText, endLine) = createRefs()

        Spacer(
            modifier = Modifier
                .height(1.dp) // you can use your .dp if responsive
                .constrainAs(startLine) {
                    start.linkTo(parent.start)
                    end.linkTo(orText.start)
                    centerVerticallyTo(orText)
                    width = androidx.constraintlayout.compose.Dimension.fillToConstraints
                }
                .background(grayScale.copy(0.2f))
        )

        Text(
            text = stringResource(CommonR.string.or),
            modifier = Modifier
                .constrainAs(orText) {
                    centerTo(parent)
                }
                .padding(horizontal = 10.dp),
            style = bodyRegularM4TextStyle.copy(
                color = grayScale,
                fontWeight = FontWeight.W700
            )
        )

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .constrainAs(endLine) {
                    start.linkTo(orText.end)
                    end.linkTo(parent.end)
                    centerVerticallyTo(orText)
                    width = androidx.constraintlayout.compose.Dimension.fillToConstraints
                }
                .background(grayScale.copy(0.2f))
        )
    }
}