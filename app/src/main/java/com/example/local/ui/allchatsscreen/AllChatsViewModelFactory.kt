package com.example.local.ui.allchatsscreen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.local.database.DAO

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the LocalDatabaseDao and context to the ViewModel.
 */
class AllChatsViewModelFactory(
    private val databaseDao: DAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllChatsViewModel::class.java)) {
            return AllChatsViewModel(databaseDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}