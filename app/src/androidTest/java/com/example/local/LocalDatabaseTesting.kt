package com.example.local

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.local.database.ChatUser
import com.example.local.database.DAO
import com.example.local.database.LocalDatabase
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class LocalDatabaseTesting {

    private lateinit var localDao: DAO
    private lateinit var localDb: LocalDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        localDb = Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        localDao = localDb.chatDatabaseDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        localDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val chatUser = ChatUser()
        localDao.insertChatUser(chatUser)
        val userReturn = localDao.getAllChatUsersRecent()
        assertEquals(userReturn?.businessVerified, false)
    }
}


