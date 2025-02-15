package com.ahmed.habib.utils.pickImage

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class PickCameraImage {

    fun getFileProvider(context: Context, appId: String): Uri {
        return FileProvider.getUriForFile(
            Objects.requireNonNull(context),
            "$appId.provider", createFile(context)
        )
    }

    private fun createFile(context: Context): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        return File.createTempFile(
            imageFileName,
            ".jpg",
            context.externalCacheDir
        )
    }
}