package com.example.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO {

    /**
     For ChatUser Class
     **/
    @Insert
    fun insertChatUser(chatUser: ChatUser)

    @Update
    fun updateChatUser(chatUser: ChatUser)

    @Query("SELECT * from tbl_chat_users WHERE user_id = :key")
    fun getChatUser(key: Long): ChatUser?

    @Query("DELETE FROM tbl_chat_users")
    fun clearChatUsers()

    @Query("SELECT * FROM tbl_chat_users ORDER BY last_updated_datetime DESC")
    fun getAllChatUsers(): LiveData<List<ChatUser>>

    @Query("SELECT * from tbl_chat_users WHERE user_id = :key")
    fun getChatUserWithId(key: Long): LiveData<ChatUser>

    @Query("SELECT * FROM tbl_chat_users ORDER BY last_updated_datetime DESC LIMIT 1")
    fun getAllChatUsersRecent(): ChatUser?


    /**
    For ChatMessage Class
     **/
    @Insert
    fun insertChatMessage(chatMessage: ChatMessage)

    @Update
    fun updateChatMessage(chatMessage: ChatMessage)

    @Query("SELECT * from tbl_chat_messages WHERE message_id = :key")
    fun getChatMessage(key: Long): ChatMessage?

    @Query("DELETE FROM tbl_chat_messages")
    fun clearChatMessages()

    @Query("SELECT * FROM tbl_chat_messages ORDER BY last_updated_datetime DESC")
    fun getAllChatMessages(): LiveData<List<ChatMessage>>

    @Query("SELECT * from tbl_chat_messages WHERE user_id = :key ORDER BY last_updated_datetime ASC")
    fun getChatMessageWithId(key: Long): LiveData<List<ChatMessage>>

    @Query("UPDATE tbl_chat_users set last_updated_message = :message, last_updated_datetime = :lastUpdatedTime WHERE user_id = :userId")
    fun updateChatUserLastMessage(message: String, userId: Long, lastUpdatedTime: Long)

    /**
    For MyUserProfile Class
     **/
    @Insert
    fun insertMyProfile(myUserProfile: MyUserProfile)

    @Update
    fun updateMyProfile(myUserProfile: MyUserProfile)

    @Query("SELECT * from tbl_my_user_profile WHERE my_user_id = :key")
    fun getMyUserProfile(key: Long): MyUserProfile?

    @Query("DELETE FROM tbl_my_user_profile")
    fun clearMyProfile()

    @Query("SELECT * FROM tbl_my_user_profile ORDER BY last_updated_datetime DESC LIMIT 1")
    fun getMyUpdatedUserProfile(): LiveData<MyUserProfile>

    @Query("SELECT * from tbl_my_user_profile WHERE my_user_id = :key")
    fun getMyUserProfileWithId(key: Long): LiveData<MyUserProfile>

}