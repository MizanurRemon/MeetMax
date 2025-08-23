package com.meetmax.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.meetmax.designsystem.theme.bodyMedium3TextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun TopAppBar(
    search: MutableState<String>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp)
        ) {
            val (leftIcon, textField, rightIcon) = createRefs()

            // Left image
            Image(
                painter = painterResource(DesignSystemR.drawable.ic_person_avatar),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(leftIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(32.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .constrainAs(textField) {
                        start.linkTo(leftIcon.end, margin = 8.dp)
                        end.linkTo(rightIcon.start, margin = 8.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    }
                    .clip(RoundedCornerShape(6.dp))
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(6.dp),
                        color = grayScale.copy(alpha = 0.4f)
                    )
                    .background(Color.White)
                    .padding(horizontal = 10.dp)
                    .height(32.dp)
                /* .onFocusEvent { event ->
                    // if (event.isFocused) onTouched()
                 }
                 .pointerInteropFilter {
                     if (it.action == MotionEvent.ACTION_DOWN) {
                         onTouched()
                     }
                     false
                 }*/
            ) {

                Image(
                    painter = painterResource(DesignSystemR.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                BasicTextField(
                    singleLine = true,
                    value = search.value,
                    onValueChange = {
                        search.value = it
                    },
                    textStyle = bodyMedium3TextStyle.copy(
                        color = grayScale,
                        textAlign = TextAlign.Start
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    cursorBrush = SolidColor(grayScale),
                    //modifier = Modifier.weight(1f),
                    decorationBox = { innerTextField ->
                        if (search.value.isEmpty()) {
                            Text(
                                text = stringResource(CommonR.string.search_for_something_here),
                                style = bodyMedium3TextStyle.copy(
                                    color = grayScale.copy(alpha = 0.8f),
                                    textAlign = TextAlign.Start
                                )

                            )
                        }
                        innerTextField()
                    }
                )
            }

            // Right image
            Image(
                painter = painterResource(DesignSystemR.drawable.ic_message),
                contentDescription = null,
                modifier = Modifier.constrainAs(rightIcon) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTopAppBar() {
    TopAppBar(
        search = remember { mutableStateOf("") }
    )
}