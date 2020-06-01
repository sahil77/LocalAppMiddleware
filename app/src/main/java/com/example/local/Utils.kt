package com.example.local

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.local.database.ChatMessage
import com.example.local.database.ChatUser
import com.example.local.database.DAO
import com.example.local.database.MyUserProfile
import java.io.*
import java.util.*


//Caution: To help maintain your app's performance, don't open and close the same file multiple times.

//to save an image of the Chat User in the internal storage
// Method to save an image to internal storage
fun saveImageToInternalStorage(drawable:Int, applicationContext: Context): String {
//    // Get the image from drawable resource as drawable object
//    val drawable = ContextCompat.getDrawable(applicationContext,drawableId)

    // Get the bitmap from drawable object
    val bitmap = (drawable as BitmapDrawable).bitmap

    // Get the context wrapper instance
    val wrapper = ContextWrapper(applicationContext)

    // Initializing a new file
    // The bellow line return a directory in internal storage
    var file = wrapper.getDir("images", Context.MODE_PRIVATE)


    // Create a file to save the image
    file = File(file, "${UUID.randomUUID()}.jpg")

    try {
        // Get the file output stream
        val stream: OutputStream = FileOutputStream(file)

        // Compress bitmap
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

        // Flush the stream
        stream.flush()

        // Close stream
        stream.close()
    } catch (e: IOException){ // Catch the exception
        e.printStackTrace()
    }

    // Return the saved image uri
    //return Uri.parse(file.absolutePath)
    return file.absolutePath
}

//// Display the internal storage saved image in image view
//image_view_saved.setImageURI(uri)

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = java.text.SimpleDateFormat("HH:mm")
    return format.format(date)
}

//Create Test Data when app launches
fun insertChatUser(localDao: DAO, mobileNumber: Long) {
    val chatUser = ChatUser()
    chatUser.userId = mobileNumber
    chatUser.lastMessage = "last message $mobileNumber"
    localDao.insertChatUser(chatUser)
}

fun insertMyUserProfile(localDao: DAO, mobileNumber: Long) {
    val myUserProfile = MyUserProfile()
    myUserProfile.myUserId = mobileNumber
    localDao.insertMyProfile(myUserProfile)
}

fun insertChatMessage(localDao: DAO, mobileNumber: Long, mType: Boolean, message: String) {
    val chatMessage = ChatMessage()
    chatMessage.messageInwardFlow = mType
    chatMessage.lastMessage = "last message $mobileNumber - $message"
    localDao.insertChatMessage(chatMessage)
}




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

@RequiresApi(Build.VERSION_CODES.O)
fun findAvailableInternalStorageOnDevice(applicationContext: ContextWrapper, filesDir: String){
    // App needs 10 MB within internal storage.
//    const val NUM_BYTES_NEEDED_FOR_MY_APP = 1024 * 1024 * 10L;
//
//    val storageManager = applicationContext.getSystemService<StorageManager>()!!
//    val appSpecificInternalDirUuid: UUID = storageManager.getUuidForPath(filesDir)
//    val availableBytes: Long =
//        storageManager.getAllocatableBytes(appSpecificInternalDirUuid)
//    if (availableBytes >= NUM_BYTES_NEEDED_FOR_MY_APP) {
//        storageManager.allocateBytes(
//            appSpecificInternalDirUuid, NUM_BYTES_NEEDED_FOR_MY_APP)
//    } else {
//        val storageIntent = Intent().apply {
//            action = ACTION_MANAGE_STORAGE
//        }
//        // Display prompt to user, requesting that they choose files to remove.
//    }
}






