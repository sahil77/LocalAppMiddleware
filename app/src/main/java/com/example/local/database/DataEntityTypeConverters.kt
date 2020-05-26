package com.example.local.database

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

//class DataEntityTypeConverters {
//    companion object{
//        @TypeConverter
//        @JvmStatic
//        fun fromBitmap(value:Bitmap):Drawable{
//            return BitmapDrawable(value)
//        }
//
//        @TypeConverter
//        @JvmStatic
//        fun toBitmap(value:Drawable):Bitmap{
//            val bitmap = (value as BitmapDrawable).bitmap
//            val stream = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream)
//            return bitmap
//        }
//    }
//}

class Converters {
    @TypeConverter
    fun fromBitmap(value:Bitmap):Drawable{
        return BitmapDrawable(value)
    }

    @TypeConverter
    fun toBitmap(value:Drawable):Bitmap{
        val bitmap = (value as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream)
        return bitmap
    }
}
