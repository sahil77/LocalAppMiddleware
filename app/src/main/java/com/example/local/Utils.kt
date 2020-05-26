package com.example.local

import android.R.attr
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.ByteArrayOutputStream


fun getLogoImage(url: String): ByteArray? {
//    try {
//        val imageUrl = URL(url)
//        val ucon: URLConnection = imageUrl.openConnection()
//        val `is`: InputStream = ucon.getInputStream()
//        val bis = BufferedInputStream(`is`)
//        val baf = ByteArrayBuffer(500)
//        var current = 0
//        while (bis.read().also({ current = it }) != -1) {
//            baf.append(current.toByte())
//        }
//        return baf.toByteArray()
//    } catch (e: Exception) {
//
//    }
    return null
}

// convert from bitmap to byte array
fun getBytes(bitmap: Bitmap): ByteArray? {
    val stream = ByteArrayOutputStream()
    bitmap.compress(CompressFormat.PNG, 0, stream)
    return stream.toByteArray()
}

// convert from byte array to bitmap
fun getImage(image: ByteArray): Bitmap? {
    return BitmapFactory.decodeByteArray(image, 0, image.size)
}

//return default chat user image
fun getDefaultChatUserImage(): Bitmap{
    val drawable: Drawable = R.drawable.ic_user as Drawable
    val bitmap = (drawable as BitmapDrawable).bitmap
    val stream = ByteArrayOutputStream()
    bitmap.compress(CompressFormat.PNG, 50, stream)
    return bitmap
}

