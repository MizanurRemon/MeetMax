package com.meetmax.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.meetmax.common.model.Comment
import com.meetmax.common.model.PostResponse
import com.meetmax.designsystem.capitalizeFirstChar
import com.meetmax.designsystem.data.POSTS
import com.meetmax.designsystem.rippleClickable
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.bodyRegularM3TextStyle
import com.meetmax.designsystem.theme.bodyRegularM4TextStyle
import com.meetmax.designsystem.theme.bodyRegularTextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.primaryBlue
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun PostCompose(response: PostResponse) {

    val openComment = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DrawableCircleImage(
                        imageUrl = response.avatar,
                        size = 32,
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = response.name,
                            style = bodyMedium1TextStyle.copy(
                                color = grayScale
                            )
                        )

                        Text(
                            text = "${response.createdTimeAgo}. ${response.visibility.capitalizeFirstChar()}",
                            style = bodyRegularM3TextStyle.copy(
                                color = grayScale.copy(alpha = .6f),
                                fontWeight = FontWeight.W500
                            )
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(DesignSystemR.drawable.ic_dots),
                        contentDescription = null
                    )
                }

                response.description?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(top = 10.dp),
                        style = bodyRegularTextStyle.copy(
                            color = grayScale,
                            fontWeight = FontWeight.W300,
                            textAlign = TextAlign.Start
                        )
                    )
                }

                if (response.postImages.isNotEmpty()) {
                    MosaicGrid(
                        photos = response.postImages
                    )

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp)
                ) {

                    if (response.reactedPersons.isNotEmpty()) {
                        OverlappingAvatars(
                            overlap = -4,
                            size = 18,
                            imageUrls = response.reactedPersons
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    if (response.comments.isNotEmpty()) {
                        Text(
                            text = "${response.comments.size} ${stringResource(CommonR.string.comments)}",
                            style = bodyRegularM3TextStyle.copy(
                                color = grayScale.copy(0.6f),
                                fontWeight = FontWeight.W300
                            ), modifier = Modifier.rippleClickable(
                                onClick = {
                                    openComment.value = !openComment.value
                                }
                            )
                        )
                    }

                    if (response.shareCount > 0) {
                        Text(
                            text = "${response.shareCount} ${stringResource(CommonR.string.share)}",
                            style = bodyRegularM3TextStyle.copy(
                                color = grayScale.copy(0.6f),
                                fontWeight = FontWeight.W300
                            ),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

                SpaceBar()

                InteractedButton(isReacted = response.isReacted)

                SpaceBar()

                WriteCommentComposeBox()

            }
        }

        if (openComment.value) {
            CommentsCompose(comments = response.comments)
        }
    }
}

@Composable
fun CommentsCompose(comments: List<Comment>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        comments.forEach { comment ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                CommentItem(comment = comment)
            }
        }
    }
}

@Composable
fun ReplyCompose(comment: String, replies: List<Comment>, authorName: String) {
    replies.forEach { reply ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
        ) {
            DrawableCircleImage(
                imageUrl = reply.avatar,
                size = 24
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(6.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .padding(vertical = 4.dp)
                    ) {
                        NameTimeCompose(name = reply.name, time = reply.createdTime)

                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .background(
                                    shape = RoundedCornerShape(6.dp),
                                    color = grayScale.copy(alpha = .05f)
                                )
                                .padding(10.dp)
                        ) {

                            Text(
                                text = "Replying to $authorName",
                                style = bodyMedium1TextStyle.copy(
                                    fontWeight = FontWeight.W500,
                                    textAlign = TextAlign.Start
                                )
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = comment,
                                style = bodyRegularM3TextStyle.copy(
                                    color = grayScale,
                                    fontWeight = FontWeight.W300,
                                    textAlign = TextAlign.Start
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        val annotateSignUpString = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = grayScale,
                                    fontWeight = FontWeight.W300
                                )
                            ) {
                                append("${reply.comment} ")
                            }


                            withStyle(
                                style = SpanStyle(
                                    color = grayScale,
                                    fontWeight = FontWeight.W500
                                )
                            ) {
                                append("@$authorName")
                            }
                        }

                        Text(
                            text = annotateSignUpString,
                            style = bodyMedium1TextStyle.copy(
                                fontWeight = FontWeight.W300,
                                textAlign = TextAlign.Start
                            )
                        )
                    }

                    Image(
                        painter = painterResource(DesignSystemR.drawable.ic_dots),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 22.dp, end = 10.dp)
                            .align(Alignment.TopEnd)
                    )
                }

                LikeReplyCompose()
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    DrawableCircleImage(
        imageUrl = comment.avatar,
        size = 24
    )

    Spacer(modifier = Modifier.width(10.dp))

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(6.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .padding(vertical = 4.dp)
            ) {
                NameTimeCompose(name = comment.name, time = comment.createdTime)

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = comment.comment,
                    style = bodyRegularM3TextStyle.copy(
                        color = grayScale,
                        fontWeight = FontWeight.W300,
                        textAlign = TextAlign.Start
                    )
                )
            }

            Image(
                painter = painterResource(DesignSystemR.drawable.ic_dots),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 22.dp, end = 10.dp)
                    .align(Alignment.TopEnd)
            )
        }

        LikeReplyCompose()

        if (comment.replies.isNotEmpty()) {
            ReplyCompose(
                comment = comment.comment,
                authorName = comment.name,
                replies = comment.replies
            )
        }
    }
}

@Composable
fun NameTimeCompose(name: String, time: String) {
    Text(
        text = name,
        style = bodyMedium1TextStyle.copy(color = grayScale)
    )

    Text(
        text = "$time ago",
        style = bodyRegularM3TextStyle.copy(
            fontWeight = FontWeight.W300,
            color = grayScale.copy(alpha = .6f)
        )
    )
}

@Composable
fun LikeReplyCompose() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Text(
            text = stringResource(CommonR.string.like),
            style = bodyRegularM4TextStyle.copy(color = grayScale.copy(.6f))
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = stringResource(CommonR.string.reply),
            style = bodyRegularM4TextStyle.copy(color = grayScale.copy(.6f))
        )
    }
}

@Composable
fun WriteCommentComposeBox() {

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (avatar, textField, sendButton) = createRefs()

        DrawableCircleImage(
            imageUrl = DesignSystemR.drawable.ic_person_avatar,
            size = 32,
            contentScale = ContentScale.Crop,
            modifier = Modifier.constrainAs(avatar) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        Box(
            modifier = Modifier.constrainAs(textField) {
                start.linkTo(avatar.end, margin = 8.dp)
                end.linkTo(sendButton.start, margin = 8.dp) // leave space for Box
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }
        ) {
            CommonTextField(
                modifier = Modifier
                    .background(color = grayScale.copy(alpha = 0.05f)),
                value = "",
                onValueChange = { },
                isTouched = false,
                isValid = true,
                onTouched = { },
                placeholder = stringResource(id = CommonR.string.write_a_comment),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 17.dp),
                horizontalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                Image(
                    painter = painterResource(DesignSystemR.drawable.ic_gif),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Image(
                    painter = painterResource(DesignSystemR.drawable.ic_picture),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

                Image(
                    painter = painterResource(DesignSystemR.drawable.ic_smile),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

            }
        }

        Box(
            modifier = Modifier
                .size(34.dp)
                .background(
                    color = primaryBlue.copy(alpha = .2f),
                    shape = RoundedCornerShape(6.dp)
                )
                .constrainAs(sendButton) {
                    end.linkTo(parent.end)
                    top.linkTo(textField.top)
                    bottom.linkTo(textField.bottom)
                }
        ) {
            Image(
                painter = painterResource(DesignSystemR.drawable.ic_send),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun InteractedButton(isReacted: Boolean) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (row1, row2, row3) = createRefs()

        Row(
            modifier = Modifier
                .constrainAs(row1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(if (isReacted) DesignSystemR.drawable.ic_heart_fill else DesignSystemR.drawable.ic_heart),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(CommonR.string.like),
                style = bodyMedium1TextStyle.copy(color = grayScale)
            )
        }

        Row(
            modifier = Modifier
                .constrainAs(row2) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(DesignSystemR.drawable.ic_comment),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(CommonR.string.comments),
                style = bodyMedium1TextStyle.copy(color = grayScale)
            )
        }

        Row(
            modifier = Modifier
                .constrainAs(row3) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(DesignSystemR.drawable.ic_share),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(CommonR.string.share),
                style = bodyMedium1TextStyle.copy(color = grayScale)
            )
        }
    }
}

@Composable
@Preview
fun PreviewPostcompose() {
    PostCompose(
        response = POSTS[1]
    )
}