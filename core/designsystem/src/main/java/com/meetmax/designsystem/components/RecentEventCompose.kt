package com.meetmax.designsystem.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.bodyMedium3TextStyle
import com.meetmax.designsystem.theme.bodyRegularM3TextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.primaryGreen
import com.meetmax.designsystem.theme.primaryRed
import com.meetmax.designsystem.utils.CEREMONY_USER_LIST
import com.meetmax.designsystem.utils.PHOTOGRAPHY_USER_LIST
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun RecentEventCompose() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(CommonR.string.recent_event),
                    style = bodyMedium1TextStyle.copy(color = grayScale)
                )

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(DesignSystemR.drawable.ic_dots),
                    contentDescription = null
                )
            }


            SpaceBar()

            EventItemCompose(
                icon = DesignSystemR.drawable.ic_book,
                primaryColor = primaryGreen,
                secondaryColor = Color.White.copy(alpha = .8f),
                title = CommonR.string.graduation_ceremony,
                description = CommonR.string.the_graduation_ceremony_is_lso_sometimes_called,
                seenList = CEREMONY_USER_LIST
            )

            Spacer(modifier = Modifier.height(14.dp))

            EventItemCompose(
                icon = DesignSystemR.drawable.ic_camera,
                primaryColor = primaryRed,
                secondaryColor = Color.White.copy(alpha = .8f),
                title = CommonR.string.photography_ideas,
                description = CommonR.string.reflections_work_because_they_can_create,
                seenList = PHOTOGRAPHY_USER_LIST
            )
        }
    }
}


@Composable
private fun EventItemCompose(
    primaryColor: Color,
    secondaryColor: Color,
    @DrawableRes icon: Int,
    @StringRes title: Int,
    @StringRes description: Int,
    seenList: List<Int> = emptyList()
) {
    Box(
        modifier = Modifier
            .background(color = Color.White)
            .clip(RoundedCornerShape(8.dp))
    ) {

        Box(
            modifier = Modifier.background(
                color = grayScale.copy(alpha = 0.03f),
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    BackgroundImageWithIconCompose(
                        icon = icon,
                        primaryColor = primaryColor,
                        secondaryColor = secondaryColor
                    )

                    Spacer(
                        modifier = Modifier.width(14.dp)
                    )

                    Column {
                        Text(
                            text = stringResource(title),
                            style = bodyMedium1TextStyle.copy(color = grayScale)
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = stringResource(description),
                            style = bodyRegularM3TextStyle.copy(
                                fontWeight = FontWeight.W500,
                                color = grayScale.copy(
                                    alpha = .6f
                                ),
                                textAlign = TextAlign.Start
                            )
                        )
                    }
                }

                SpaceBar()

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${seenList.size} ${stringResource(CommonR.string.seen)}",
                        style = bodyMedium3TextStyle.copy(color = grayScale)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    OverlappingAvatars(
                        imageUrls = seenList
                    )

                }
            }
        }

    }
}

@Composable
private fun OverlappingAvatars(
    imageUrls: List<Int>
) {
    val overlap = -4
    val size = 18
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


@Preview
@Composable
fun PreviewRecentEventCompose() {
    RecentEventCompose()
}