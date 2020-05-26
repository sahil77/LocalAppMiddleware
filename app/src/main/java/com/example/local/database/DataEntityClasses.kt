package com.example.local.database

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(tableName = "tbl_chat_users")
@TypeConverters(Converters::class)
data class ChatUser(
    @PrimaryKey
    @ColumnInfo(name="user_id")
    var userId: Long = 0L,

    @ColumnInfo(name = "user_name")
    val userName: String = "",

    //setting the same to default for and will change later when the image part will be implemented
    @ColumnInfo(name = "user_image")
    var userImage: Bitmap,
    //var userImage: Bitmap = getDefaultChatUserImage(),

    @ColumnInfo(name = "last_updated_message")
    val lastMessage: String = "",

    @ColumnInfo(name = "last_updated_datetime")
    val lastUpdatedTime: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "business_profile")
    val businessProfile: Boolean = false,

    @ColumnInfo(name = "business_category")
    val businessCategory: String = "NA",

    @ColumnInfo(name = "verified")
    val businessVerified: Boolean = false

)

@Entity(tableName = "tbl_chat_messages")
data class ChatMessage(
    @PrimaryKey
    @ColumnInfo(name="message_id")
    var messageId: Long = 0L,

    //This will persist the user id of the user to whom message is sent when message_inward_flow is false
    //This will persist the user id of the user who sent the message when message_inward_flow is true
    @ColumnInfo(name="user_id")
    var userId: Long = 0L,

    //Message inward flow is true if message received and false when message sent
    @ColumnInfo(name="message_inward_flow")
    var messageInwardFlow: Boolean = true,

    //Message Type as TEXT or MEDIA or AUDIO or VIDEO
    @ColumnInfo(name="message_type")
    var messageType: String = "TEXT",

    @ColumnInfo(name = "message")
    val lastMessage: String = "",

    @ColumnInfo(name = "last_updated_datetime")
    val lastUpdatedTime: Long = System.currentTimeMillis()

)

@Entity(tableName = "tbl_my_user_profile")
@TypeConverters(Converters::class)
data class MyUserProfile(
    @PrimaryKey
    @ColumnInfo(name="my_user_id")
    var myUserId: Long = 0L,

    @ColumnInfo(name = "my_user_name")
    val myUserName: String = "",

    //setting the same to default for and will change later when the image part will be implemented
    @ColumnInfo(name = "my_user_image")
    var userImage: Bitmap,
    //var userImage: Bitmap = getDefaultChatUserImage(),

    @ColumnInfo(name = "last_updated_datetime")
    val lastUpdatedTime: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "business_profile")
    val myBusinessProfile: Boolean = false,

    @ColumnInfo(name = "business_category")
    val myBusinessCategory: String = "NA",

    @ColumnInfo(name = "verified")
    val myBusinessVerified: Boolean = false

)
