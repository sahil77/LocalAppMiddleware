package com.example.local.ui.allchatsscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.local.database.LocalDatabase
import com.example.local.R
import com.example.local.databinding.AllChatsFragmentBinding

class AllChatsFragment : Fragment() {

//private lateinit var allChatsViewModel: AllChatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment layout view
        val binding: AllChatsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.all_chats_fragment, container, false)

        //Application Context object
        val application = requireNotNull(this.activity).application

        //Database is created when this is called
        val dataSourceDao = LocalDatabase.getInstance(application).chatDatabaseDAO

        val viewModelFactory = AllChatsViewModelFactory(dataSourceDao, application)

        val allChatsViewModel= ViewModelProviders.of(
                this, viewModelFactory).get(AllChatsViewModel::class.java)

        binding.viewModel = allChatsViewModel
        binding.lifecycleOwner = this

        //For actual implementation, passing the click event value to the View Model
        val adapter = ChatUserAdapter(ChatUserListener {
                //passing on the chat user clicked listener along with the method of click in view model
                userId ->  allChatsViewModel.onChatClicked(userId)
        })

        //Attaching the adapter with the recycler view of the all chats fragment
        binding.allChatsRecyclerView.adapter = adapter

        //observer so that we know when the flag is updated for navigation (click event of list item in recycler view)
        //and, performs the navigation as navigation is the responsibility of the fragment to carry out
        allChatsViewModel.navigateToDetailedChat.observe(this, Observer {chatUser ->
            chatUser?.let {
                this.findNavController().navigate(AllChatsFragmentDirections.actionAllChatsFragmentToDetailedChatFragment(chatUser))
                //calling this function again to set the value back to null for next navigation
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                allChatsViewModel.onChatClickedNavigated()
            }
        })


        //returns the view that got bind
        return binding.root
    }

}
