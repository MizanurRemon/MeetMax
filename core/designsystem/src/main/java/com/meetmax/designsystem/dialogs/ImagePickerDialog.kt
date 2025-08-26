package com.meetmax.designsystem.dialogs

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.meetmax.designsystem.createImageFile
import java.util.Objects
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR

@Composable
fun ImagePickerDialog(openDialog: MutableState<Boolean>, onDoneClick: (Uri) -> Unit) {

    val context = LocalContext.current
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            imageUri = uri
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }

    }

    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri!!
        }


    if (openDialog.value) {
        Dialog(
            onDismissRequest = { openDialog.value = false },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(bottom = 10.dp)
                    ) {
                        Image(
                            painter = if (imageUri == null) painterResource(id = DesignSystemR.drawable.ic_camera) else rememberAsyncImagePainter(
                                model = imageUri
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxHeight()
                                .padding(10.dp)
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color.Gray)
                        //.padding(vertical = 5.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Icon(
                            painter = painterResource(id = DesignSystemR.drawable.ic_check),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    if (imageUri == null) {
                                        Toast
                                            .makeText(
                                                context,
                                                "Please select image",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    } else {
                                        openDialog.value = false
                                        onDoneClick(imageUri!!)
                                    }
                                },
                            tint = Color.Green
                        )

                        Icon(
                            painter = painterResource(id = DesignSystemR.drawable.ic_camera),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    val permissionCheckResult = ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.CAMERA
                                    )

                                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                        cameraLauncher.launch(uri)
                                    } else {
                                        permissionLauncher.launch(Manifest.permission.CAMERA)
                                    }
                                }
                        )

                        Icon(
                            painter = painterResource(id = DesignSystemR.drawable.ic_picture),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    galleryLauncher.launch("image/*")
                                },
                        )

                        Icon(
                            painter = painterResource(id = DesignSystemR.drawable.ic_close),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    openDialog.value = false
                                },
                            tint = Color.Red
                        )

                    }

                }
            }
        }
    }

}