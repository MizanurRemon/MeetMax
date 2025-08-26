package com.meetmax.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.meetmax.designsystem.theme.bodyMedium1TextStyle
import com.meetmax.designsystem.theme.bodyMedium3TextStyle
import com.meetmax.designsystem.theme.bodyRegularM3TextStyle
import com.meetmax.designsystem.theme.grayScale
import com.meetmax.designsystem.theme.primaryBlue
import com.meetmax.designsystem.theme.primaryYellow
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun BirthdayCompose() {
    val count = 2

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
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(CommonR.string.birthdays),
                    style = bodyMedium1TextStyle.copy(color = grayScale)
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = stringResource(CommonR.string.see_all),
                    style = bodyMedium3TextStyle.copy(
                        color = primaryBlue
                    )
                )
            }

            SpaceBar()

            Column(modifier = Modifier.fillMaxWidth()) {

                Row(verticalAlignment = Alignment.CenterVertically) {


                    DrawableCircleImage(
                        imageUrl = DesignSystemR.drawable.ic_person_avatar,
                        size = 45,
                        shape = RoundedCornerShape(4.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = "Edilson De Carvalho",
                            style = bodyMedium1TextStyle.copy(color = grayScale)
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = stringResource(CommonR.string.birthday_today),
                            style = bodyRegularM3TextStyle.copy(color = grayScale.copy(alpha = .6f))
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val (textField, sendButton) = createRefs()

                    CommonTextField(
                        modifier = Modifier
                            .constrainAs(textField) {
                                start.linkTo(parent.start)
                                end.linkTo(sendButton.start, margin = 8.dp) // leave space for Box
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.fillToConstraints
                            }
                            .background(color = grayScale.copy(alpha = 0.05f)),
                        value = "",
                        onValueChange = { },
                        isTouched = false,
                        isValid = true,
                        onTouched = { },
                        placeholder = stringResource(id = CommonR.string.write_on_his_inbox),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )

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

                Spacer(modifier = Modifier.height(14.dp))

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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp)

                        ) {
                            BackgroundImageWithIconCompose(
                                icon = DesignSystemR.drawable.ic_birthday,
                                primaryColor = primaryYellow,
                                secondaryColor = Color.White.copy(alpha = .8f)
                            )

                            Spacer(
                                modifier = Modifier.width(14.dp)
                            )

                            Column {
                                Text(
                                    text = stringResource(CommonR.string.upcoming_birthdays),
                                    style = bodyMedium1TextStyle.copy(color = grayScale)
                                )

                                Spacer(
                                    modifier = Modifier.height(6.dp)
                                )

                                Text(
                                    text = pluralStringResource(
                                        id = CommonR.plurals.upcoming_birthday_list,
                                        count = count,
                                        formatArgs = arrayOf(count)
                                    ),
                                    style = bodyMedium1TextStyle.copy(
                                        color = grayScale.copy(
                                            alpha = .6f
                                        ),
                                        textAlign = TextAlign.Start
                                    )
                                )
                            }
                        }
                    }

                }

            }
        }

    }
}



@Composable
@Preview
fun PreviewBirthdayCompose() {
    BirthdayCompose()
}