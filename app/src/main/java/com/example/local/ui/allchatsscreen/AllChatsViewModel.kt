package com.example.local.ui.allchatsscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.local.database.DAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

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

