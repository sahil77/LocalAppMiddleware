package com.example.local.ui.allchatsscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.local.database.ChatUser
import com.example.local.database.DAO
import com.example.local.insertChatMessage
import com.example.local.insertChatUser
import com.example.local.insertMyUserProfile
import kotlinx.coroutines.*

class AllChatsViewModel(
    databaseDao: DAO, application: Application) : AndroidViewModel(application) {

    /**
     * viewModelJob allows us to cancel all coroutines started by this ViewModel.
     */
    private var allChatsViewModelJob = Job()


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
    private val uiScope = CoroutineScope(Dispatchers.Main + allChatsViewModelJob)

    val allChatUsers = databaseDao.getAllChatUsers()

    /** Only for testing**/

    fun addUsersForTest(dataSourceDao: DAO) {
        uiScope.launch {
            addUsersTemp(dataSourceDao)
        }
    }

    private suspend fun addUsersTemp(dataSourceDao: DAO) {
        withContext(Dispatchers.IO) {
            insertChatUser(dataSourceDao, 9899929920)
            insertChatUser(dataSourceDao, 9899929921)
            insertChatUser(dataSourceDao, 9899929922)
            insertChatUser(dataSourceDao, 9899929923)
            insertChatUser(dataSourceDao, 9899929924)
            insertChatUser(dataSourceDao, 9899929925)
            insertChatUser(dataSourceDao, 9899929926)
            insertChatUser(dataSourceDao, 9899929927)
            insertChatUser(dataSourceDao, 9899929929)
            insertChatUser(dataSourceDao, 9899929933)
            insertChatUser(dataSourceDao, 9899929934)
            insertChatUser(dataSourceDao, 9899929935)
            insertChatUser(dataSourceDao, 9899929936)
            insertChatUser(dataSourceDao, 9899929937)

            insertMyUserProfile(dataSourceDao, 9899929920)

            insertChatMessage(dataSourceDao, 9899929921, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929920, false, "I am good thanks")
            insertChatMessage(dataSourceDao, 9899929921, true, "Where are u these days?")
            insertChatMessage(dataSourceDao, 9899929922, true, "Hi Sunny")
            insertChatMessage(dataSourceDao, 9899929923, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929933, false, "Hello")
            insertChatMessage(dataSourceDao, 9899929921, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929920, false, "I am good thanks")
            insertChatMessage(dataSourceDao, 9899929921, false, "Where are u these days?")
            insertChatMessage(dataSourceDao, 9899929922, true, "Hi Sunny")
            insertChatMessage(dataSourceDao, 9899929923, false, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929933, true, "Hello")
            insertChatMessage(dataSourceDao, 9899929921, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929920, false, "I am good thanks")
            insertChatMessage(dataSourceDao, 9899929921, true, "Where are u these days?")
            insertChatMessage(dataSourceDao, 9899929922, true, "Hi Sunny")
            insertChatMessage(dataSourceDao, 9899929923, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929933, false, "Hello")
            insertChatMessage(dataSourceDao, 9899929921, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929920, false, "I am good thanks")
            insertChatMessage(dataSourceDao, 9899929921, false, "Where are u these days?")
            insertChatMessage(dataSourceDao, 9899929922, true, "Hi Sunny")
            insertChatMessage(dataSourceDao, 9899929923, false, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929933, true, "Hello")
            insertChatMessage(dataSourceDao, 9899929921, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929920, false, "I am good thanks")
            insertChatMessage(dataSourceDao, 9899929921, true, "Where are u these days?")
            insertChatMessage(dataSourceDao, 9899929922, true, "Hi Sunny")
            insertChatMessage(dataSourceDao, 9899929923, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929933, false, "Hello")
            insertChatMessage(dataSourceDao, 9899929921, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929920, false, "I am good thanks")
            insertChatMessage(dataSourceDao, 9899929921, false, "Where are u these days?")
            insertChatMessage(dataSourceDao, 9899929922, true, "Hi Sunny")
            insertChatMessage(dataSourceDao, 9899929923, false, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929933, true, "Hello")
            insertChatMessage(dataSourceDao, 9899929921, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929920, false, "I am good thanks")
            insertChatMessage(dataSourceDao, 9899929921, true, "Where are u these days?")
            insertChatMessage(dataSourceDao, 9899929922, true, "Hi Sunny")
            insertChatMessage(dataSourceDao, 9899929923, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929933, false, "Hello")
            insertChatMessage(dataSourceDao, 9899929921, true, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929920, false, "I am good thanks")
            insertChatMessage(dataSourceDao, 9899929921, false, "Where are u these days?")
            insertChatMessage(dataSourceDao, 9899929922, true, "Hi Sunny")
            insertChatMessage(dataSourceDao, 9899929923, false, "Hi Sahil")
            insertChatMessage(dataSourceDao, 9899929933, true, "Hello")



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


    override fun onCleared() {
        super.onCleared()
        allChatsViewModelJob.cancel()
    }

}

