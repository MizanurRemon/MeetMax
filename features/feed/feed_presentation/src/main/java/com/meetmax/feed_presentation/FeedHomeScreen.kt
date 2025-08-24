package com.meetmax.feed_presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.meetmax.designsystem.components.DrawableCircleImage
import com.meetmax.designsystem.theme.BACKGROUND_COLOR
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.primaryBlue
import kotlinx.coroutines.delay
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun FeedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BACKGROUND_COLOR),
    ) {
        UserComponent()
    }
}

@Composable
fun UserComponent() {

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
    FeedScreen()
}