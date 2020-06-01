package com.example.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.local.saveImageToInternalStorage
import java.util.*

@Entity(tableName = "tbl_chat_users")
data class ChatUser(
    @PrimaryKey
    @ColumnInfo(name="user_id")
    var userId: Long = 0L,

    @ColumnInfo(name = "user_name")
    var userName: String = "",

    //setting the same to default for and will change later when the image part will be implemented
    //will keep the userId as the name of the file for the user image and will save the file on
    //internal storage of app using the same name
    @ColumnInfo(name = "user_image_APath")
    var userImage: String = "default",
    //var userImage: Bitmap = getDefaultChatUserImage(),

    @ColumnInfo(name = "last_updated_message")
    var lastMessage: String = "",

    @ColumnInfo(name = "last_updated_datetime")
    var lastUpdatedTime: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "business_profile")
    var businessProfile: Boolean = false,

    @ColumnInfo(name = "business_category")
    var businessCategory: String = "NA",

    @ColumnInfo(name = "verified")
    var businessVerified: Boolean = false

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
    var lastMessage: String = "",

    @ColumnInfo(name = "last_updated_datetime")
    var lastUpdatedTime: Long = System.currentTimeMillis(),

    //0 --> Not sent, 1 --> Sent, 2 --> Delivered, and 3 --> Read
    @ColumnInfo(name = "message_status")
    var messageStatus: Int = 0
)

@Entity(tableName = "tbl_chat_messages_status")
data class ChatMessageStatus(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="message_status_id")
    var messageStatusId: Long,

    @ColumnInfo(name="message_id")
    var messageId: Long = 0L,

    //0 --> Not sent, 1 --> Sent, 2 --> Delivered, and 3 --> Read
    @ColumnInfo(name = "message_status")
    var messageStatus: Int = 0,

    @ColumnInfo(name = "last_updated_datetime")
    var messageLastUpdatedTime: Long = System.currentTimeMillis()
)

@Entity(tableName = "tbl_my_user_profile")
data class MyUserProfile(
    @PrimaryKey
    @ColumnInfo(name="my_user_id")
    var myUserId: Long = 0L,

    @ColumnInfo(name = "my_user_name")
    var myUserName: String = "",

    //setting the same to default for and will change later when the image part will be implemented
    @ColumnInfo(name = "user_image_APath")
    var userImage: String = "default",
    //var userImage: Bitmap = getDefaultChatUserImage(),

    @ColumnInfo(name = "last_updated_datetime")
    var lastUpdatedTime: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "business_profile")
    var myBusinessProfile: Boolean = false,

    @ColumnInfo(name = "business_category")
    var myBusinessCategory: String = "NA",

    @ColumnInfo(name = "verified")
    var myBusinessVerified: Boolean = false

)
