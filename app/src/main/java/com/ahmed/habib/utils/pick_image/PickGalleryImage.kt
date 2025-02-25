package com.ahmed.habib.utils.pick_image

import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts

class PickGalleryImage {
    fun pickImageFromGallery() =
        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
}