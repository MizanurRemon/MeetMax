package com.meetmax.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.heading1TextStyle
import com.meetmax.designsystem.theme.heading3TextStyle


@Composable
fun MosaicGrid(
    photos: List<Int>,
    gap: Dp = 12.dp,
    rowHeight: Dp = 185.dp
) {

    MosaicRow(
        chunk = photos,
        gap = gap,
        rowHeight = rowHeight
    )
}

@Composable
private fun MosaicRow(
    chunk: List<Int>,
    gap: Dp,
    rowHeight: Dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .height(rowHeight),
        horizontalArrangement = Arrangement.spacedBy(gap),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when {

            chunk.size > 3 -> {
                MosaicBigImage(
                    photo = chunk[0],
                    modifier = Modifier.weight(1f)
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(gap)
                ) {
                    MosaicSmallImage(
                        photo = chunk[1],
                        modifier = Modifier.weight(1f)
                    )
                    Box(modifier = Modifier.weight(1f)) {
                        MosaicSmallImage(
                            photo = chunk[2],
                            modifier = Modifier.fillMaxSize()
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = grayScale.copy(alpha = .5f),
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            Text(
                                text = "+${chunk.size - 3}",
                                style = heading1TextStyle.copy(color = Color.White),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }

            chunk.size == 3 -> {
                MosaicBigImage(
                    photo = chunk[0],
                    modifier = Modifier.weight(1f)
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(gap)
                ) {
                    MosaicSmallImage(
                        photo = chunk[1],
                        modifier = Modifier.weight(1f)
                    )
                    MosaicSmallImage(
                        photo = chunk[2],
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            chunk.size == 2 -> {
                Row(horizontalArrangement = Arrangement.spacedBy(gap)) {
                    MosaicBigImage(
                        photo = chunk[0],
                        modifier = Modifier.weight(1f)
                    )

                    MosaicBigImage(
                        photo = chunk[1],
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            chunk.size == 1 -> {
                MosaicBigImage(
                    photo = chunk[0],
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun MosaicBigImage(
    photo: Int,
    modifier: Modifier = Modifier
) {
    DrawableCircleImage(
        imageUrl = photo,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxHeight(),
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
private fun MosaicSmallImage(
    photo: Int,
    modifier: Modifier = Modifier
) {
    DrawableCircleImage(
        imageUrl = photo,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    )
}
