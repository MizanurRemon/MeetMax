package com.meetmax.feed_presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meetmax.designsystem.components.AppActionButton
import com.meetmax.designsystem.components.CommonTextField
import com.meetmax.designsystem.components.DrawableCircleImage
import com.meetmax.designsystem.rippleClickable
import com.meetmax.designsystem.theme.BACKGROUND_COLOR
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.bodyRegularM3TextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.primaryBlue
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun FeedScreen() {
    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BACKGROUND_COLOR),
    ) {
        StorySelection()

        PostInput(
            onClick = {
                showSheet = true
            }
        )

        //CreatePostCompose()
    }

    if (showSheet) {
        CreatePostCompose(
            onBack = {
                showSheet = false
            }
        )
    }
}

@Composable
fun PostInput(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                DrawableCircleImage(
                    imageUrl = DesignSystemR.drawable.ic_person_avatar,
                    size = 32,
                    shape = CircleShape,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))

                CommonTextField(
                    readOnly = true,
                    value = "",
                    onValueChange = {
                        //onEvent(SignUpEvent.OnDateEnter(it))
                    },
                    isTouched = false,
                    isValid = true,
                    onTouched = {
                        onClick()
                    },
                    placeholder = stringResource(id = CommonR.string.whats_happening),
                    leadingIcon = null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    textStyle = bodyRegularM3TextStyle.copy(
                        textAlign = TextAlign.Start,
                        color = grayScale.copy(alpha = 0.6f)
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PostItemButton(
                    title = CommonR.string.live,
                    icon = DesignSystemR.drawable.ic_video_camera,
                    onClick = {

                    }
                )

                Spacer(modifier = Modifier.width(24.dp))

                PostItemButton(
                    title = CommonR.string.photo,
                    icon = DesignSystemR.drawable.ic_picture,
                    onClick = {

                    }
                )

                Spacer(modifier = Modifier.width(24.dp))

                PostItemButton(
                    title = CommonR.string.feeling,
                    icon = DesignSystemR.drawable.ic_smile,
                    onClick = {

                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                AppActionButton(
                    radius = 16,
                    textStyle = bodyMedium1TextStyle.copy(
                        color = Color.White
                    ),
                    modifier = Modifier.wrapContentWidth(),
                    text = CommonR.string.post,
                    onClick = {

                    }
                )
            }

        }
    }
}

@Composable
fun PostItemButton(title: Int, icon: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier.rippleClickable {
            onClick()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(title),
            style = bodyRegularM3TextStyle.copy(
                fontWeight = FontWeight.W500,
                color = grayScale
            )
        )
    }
}


@Composable
fun StorySelection() {

    val listState = rememberLazyListState()


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {


        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            itemsIndexed(USERS) { index, item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(
                        start = if (index == 0) 20.dp else 0.dp,
                        end = if (index == USERS.size - 1) 20.dp else 0.dp
                    )
                ) {
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {

                        DrawableCircleImage(
                            imageUrl = item.avatar,
                            shape = CircleShape,
                            size = 50,
                            borderWidth = 2.dp,
                            borderColor = primaryBlue,
                        )

                        if (item.isMyself) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .offset(y = 8.dp)
                            ) {
                                Image(
                                    painter = painterResource(DesignSystemR.drawable.ic_add),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .background(color = Color.White, shape = CircleShape)
                                        .padding(3.dp)
                                        .align(Alignment.Center),
                                    colorFilter = ColorFilter.tint(
                                        color = grayScale
                                    )
                                )
                            }
                        }


                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = item.name,
                        style = bodyMedium1TextStyle.copy(
                            color = grayScale
                        )
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun PreviewFeedScreen() {
    PostInput(
        onClick = {}
    )
}