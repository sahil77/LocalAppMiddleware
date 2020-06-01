package com.example.local.ui.detailedchatscreen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.local.database.DAO
import com.example.local.ui.allchatsscreen.AllChatsViewModel

class DetailedChatViewModelFactory(
    private val databaseDao: DAO,
    private val userId: Long,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailedChatViewModel::class.java)) {
            return DetailedChatViewModel(databaseDao, userId, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}