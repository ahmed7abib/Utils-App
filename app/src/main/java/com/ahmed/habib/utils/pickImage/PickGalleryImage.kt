package com.ahmed.habib.utils.pickImage

import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts

class PickGalleryImage {
    fun pickImageFromGallery() =
        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
}