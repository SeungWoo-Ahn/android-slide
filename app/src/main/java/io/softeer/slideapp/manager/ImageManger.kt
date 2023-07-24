package io.softeer.slideapp.manager

import android.content.Intent
import android.net.Uri
import io.softeer.slideapp.ui.MainActivity

class ImageManger {

    fun pickImageFromGallery(
        activity: MainActivity,
        onSuccess: (ByteArray?) -> Unit
    ) {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        activity.goActivityForResult(intent) {
            val imageUri = it?.data
            if (imageUri != null) {
                onSuccess(imageUriToByteArray(activity, imageUri))
            }
        }

    }

    private fun imageUriToByteArray(activity: MainActivity, imageUri: Uri): ByteArray {
        val inputStream = activity.contentResolver.openInputStream(imageUri)
        return inputStream?.readBytes() ?: byteArrayOf()
    }
}