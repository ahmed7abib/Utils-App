package com.ahmed.habib.utils.pickImage.ui

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ahmed.habib.core.composeable.CommonRoundedButton
import com.ahmed.habib.core.utils.openAppSettings
import com.ahmed.habib.core.utils.showConfirmationDialog
import com.ahmed.habib.utils.R
import com.ahmed.habib.utils.pickImage.PickCameraImage

@Composable
fun PickCameraImageLayout(
    pickCameraImage: PickCameraImage = PickCameraImage()
) {

    val context = LocalContext.current
    val uri = pickCameraImage.getFileProvider(context, context.packageName)
    var imagePicked by remember { mutableStateOf<Uri?>(Uri.EMPTY) }

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            if (it) {
                imagePicked = uri
            }
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            imageLauncher.launch(uri)
        } else {
            context.showConfirmationDialog(
                context.getString(R.string.open_camera_permission),
                context.getString(R.string.app_name)
            ) {
                context.openAppSettings()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        AsyncImage(
            model = imagePicked,
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.heightIn(30.dp))

        CommonRoundedButton(
            name = "Pick Camera Image",
            fontSize = 14.sp,
            mainColor = R.color.purple_200,
            textColor = R.color.white
        ) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}
