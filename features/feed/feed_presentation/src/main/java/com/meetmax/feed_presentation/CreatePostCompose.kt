package com.meetmax.feed_presentation

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meetmax.designsystem.components.AppActionButton
import com.meetmax.designsystem.components.DrawableCircleImage
import com.meetmax.designsystem.components.DrawableCircleUriImage
import com.meetmax.designsystem.dialogs.ImagePickerDialog
import com.meetmax.designsystem.rippleClickable
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.bodyMedium3TextStyle
import com.meetmax.designsystem.theme.bodyRegular4TextStyle
import com.meetmax.designsystem.theme.bodyRegularM4TextStyle
import com.meetmax.designsystem.theme.bodyRegularTextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.primaryBlue
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR


@Composable
fun CreatePostCompose(
    onBack: () -> Unit
) {

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val openImagePickerDialog = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopNavBox(
                onBack = {
                    onBack()
                }
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = grayScale.copy(alpha = .2f))
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        PostBox(
            selectedImageUri = imageUri,
            onClearImage = { imageUri = null },
            onImageSelection = {
                openImagePickerDialog.value = true
            })
    }

    BackHandler {
        onBack()
    }

    if (openImagePickerDialog.value) {
        ImagePickerDialog(openDialog = openImagePickerDialog, onDoneClick = { image ->
            imageUri = image
            openImagePickerDialog.value = false
        })
    }
}

@Composable
fun PostBox(
    onImageSelection: () -> Unit,
    onClearImage: () -> Unit,
    selectedImageUri: Uri?,
    minLines: Int = 3,
    maxLines: Int = 8
) {
    val text = remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.Top) {
            DrawableCircleImage(
                imageUrl = DesignSystemR.drawable.ic_person_avatar,
                size = 32,
                shape = CircleShape
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier
                    .background(
                        color = grayScale.copy(alpha = .05f),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(10.dp)
            ) {
                TextField(
                    value = text.value,
                    onValueChange = {
                        text.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.Transparent,
                        )
                        .heightIn(min = (minLines * 24).dp, max = (maxLines * 24).dp),
                    placeholder = {
                        Text(
                            text = stringResource(CommonR.string.whats_happening),
                            style = bodyRegularTextStyle.copy(
                                color = grayScale.copy(alpha = .5f),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.W300
                            )
                        )
                    },
                    textStyle = bodyRegularTextStyle.copy(
                        color = grayScale,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.W300
                    ),
                    singleLine = false,
                    minLines = minLines,
                    maxLines = maxLines,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Default
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = grayScale.copy(alpha = .05f),
                        unfocusedContainerColor = grayScale.copy(alpha = .05f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                        /*focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = grayScale.copy(alpha = 0.4f),
                        cursorColor = primaryBlue,
                        focusedLabelColor = primaryBlue,
                        unfocusedLabelColor = grayScale.copy(alpha = 0.7f)*/
                    ),
                    shape = RoundedCornerShape(6.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                selectedImageUri?.let {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(168.dp)
                    ) {

                        DrawableCircleUriImage(
                            imageUrl = selectedImageUri,
                            modifier = Modifier.fillMaxSize(),
                            shape = RoundedCornerShape(6.dp),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.TopEnd)
                                .rippleClickable { onClearImage() }
                        ) {
                            Image(
                                painter = painterResource(DesignSystemR.drawable.ic_close),
                                contentDescription = null,
                                modifier = Modifier
                                    .border(width = 2.dp, color = grayScale, shape = CircleShape)
                                    .size(16.dp)
                                    .padding(3.dp)
                            )
                        }
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        FeatureItem(
            icon = DesignSystemR.drawable.ic_video_camera,
            text = CommonR.string.live_video,
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        FeatureItem(
            icon = DesignSystemR.drawable.ic_picture,
            text = CommonR.string.photo_video,
            onClick = {
                onImageSelection()
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        FeatureItem(
            icon = DesignSystemR.drawable.ic_smile,
            text = CommonR.string.feeling,
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        AppActionButton(
            text = CommonR.string.post,
            bgColor = primaryBlue,
            onClick = {

            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = bodyMedium1TextStyle.copy(
                color = Color.White
            )
        )
    }
}

@Composable
fun FeatureItem(onClick: () -> Unit, @StringRes text: Int, @DrawableRes icon: Int) {
    Row(
        modifier = Modifier.rippleClickable {
            onClick()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(text),
            style = bodyMedium3TextStyle.copy(
                color = grayScale
            )
        )

    }
}

@Composable
fun TopNavBox(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(DesignSystemR.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .rippleClickable(
                    onClick = {
                        onBack()
                    }
                )
        )

        Text(
            text = stringResource(CommonR.string.create_a_post),
            style = bodyMedium1TextStyle.copy(color = grayScale),
            modifier = Modifier.padding(start = 10.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(CommonR.string.visible_for),
            style = bodyRegular4TextStyle.copy(
                color = grayScale,
                fontWeight = FontWeight.W500
            ),
            modifier = Modifier.padding(start = 10.dp)
        )

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Box(
            modifier = Modifier.background(
                shape = RoundedCornerShape(3.dp),
                color = grayScale.copy(.05f)
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 10.dp)
                    .align(alignment = Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(CommonR.string.friends),
                    style = bodyRegularM4TextStyle.copy(
                        color = primaryBlue,
                        fontWeight = FontWeight.W500
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Image(
                    painter = painterResource(DesignSystemR.drawable.ic_angle_down),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewCreatePost() {
    CreatePostCompose(
        onBack = {}
    )
}