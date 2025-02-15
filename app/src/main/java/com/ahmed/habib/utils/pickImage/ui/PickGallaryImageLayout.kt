package com.ahmed.habib.utils.pickImage.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ahmed.habib.core.composeable.CommonRoundedButton
import com.ahmed.habib.utils.R
import com.ahmed.habib.utils.pickImage.PickGalleryImage

@Composable
fun PickImageLayout(pickGalleryImage: PickGalleryImage = PickGalleryImage()) {

    var singleImageSelected by remember { mutableStateOf<Uri?>(null) }
    var imageSelected by remember { mutableStateOf<List<Uri>>(emptyList()) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> singleImageSelected = uri }
    )

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> imageSelected = uris }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        AsyncImage(
            model = singleImageSelected,
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.heightIn(30.dp))

        CommonRoundedButton(
            name = "Select Single Img",
            fontSize = 14.sp,
            mainColor = R.color.purple_200,
            textColor = R.color.white
        ) {
            singlePhotoPickerLauncher.launch(pickGalleryImage.pickImageFromGallery())
        }

        Spacer(modifier = Modifier.heightIn(8.dp))

        CommonRoundedButton(
            name = "Select Multi Images",
            fontSize = 14.sp,
            mainColor = R.color.purple_200,
            textColor = R.color.white
        ) {
            multiplePhotoPickerLauncher.launch(pickGalleryImage.pickImageFromGallery())
        }
    }
}