package com.example.local.ui.detailedchatscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.local.database.ChatMessage
import com.example.local.database.ChatUser
import com.example.local.database.DAO
import kotlinx.coroutines.*
import java.util.*

class DetailedChatViewModel (
    databaseDao: DAO, userId: Long, application: Application
) : AndroidViewModel(application) {

    /**
     * viewModelJob allows us to cancel all coroutines started by this ViewModel.
     */
    private var detailedChatViewModelJob = Job()


    /**
     * A [Co routine Scope] keeps track of all coroutines started by this ViewModel.
     *
     * Because we pass it [allChatsViewModelJob], any coroutine started in this uiScope can be cancelled
     * by calling `allChatsViewModelJob.cancel()`
     *
     * By default, all co routines started in uiScope will launch in [Dispatchers.Main] which is
     * the main thread on Android. This is a sensible default because most coroutines started by
     * a [ViewModel] update the UI after performing some processing.
     */
    private val uiScope = CoroutineScope(Dispatchers.Main + detailedChatViewModelJob)

    val allChatMessages = databaseDao.getChatMessageWithId(userId)

    /** Only for testing**/

    fun addUsersForTest(dataSourceDao: DAO) {
        uiScope.launch {
            addUsersTemp(dataSourceDao)
        }
    }

    private suspend fun addUsersTemp(dataSourceDao: DAO) {
        withContext(Dispatchers.IO) {
            //To-do
        }
    }

    /** Handling the click event of the Chat list item in the recycler view of All Chats Fragment
     * **/
    //To navigate when list item in recycler view is clicked
    private val _navigateToDetailedChat = MutableLiveData<Long>()
    val navigateToDetailedChat
        get() = _navigateToDetailedChat

    //Handling the click event of the list item in the recycler view
    fun onChatClicked(id: Long){
        _navigateToDetailedChat.value = id
    }

    //when done navigating
    fun onChatClickedNavigated() {
        _navigateToDetailedChat.value = null
    }

    fun sendMessageClicked(dataSourceDao: DAO, userId: Long, userMessage: String) {
        uiScope.launch {
            sendMessage(dataSourceDao, userId, userMessage)
        }
    }

    private suspend fun sendMessage(dataSourceDao: DAO, userId: Long, userMessage: String) {
        withContext(Dispatchers.IO) {
            val uniqueMessageID: Long = generateUniqueId()
            val message: ChatMessage = ChatMessage(uniqueMessageID, userId, false, "TEXT", userMessage)
            dataSourceDao.insertChatMessage(message)
            dataSourceDao.updateChatUserLastMessage(userMessage, userId, System.currentTimeMillis())
        }
    }

    private fun generateUniqueId(): Long {
        var value: Long = -1
        do {
            value = UUID.randomUUID().mostSignificantBits
        } while (value < 0)
        return value
    }


    override fun onCleared() {
        super.onCleared()
        detailedChatViewModelJob.cancel()
    }


}