package com.example.local.ui.detailedchatscreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.local.R
import com.example.local.database.LocalDatabase
import com.example.local.databinding.DetailedChatFragmentBinding


class DetailedChatFragment : Fragment() {

    companion object {
        fun newInstance() = DetailedChatFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val bindingDetailedChat: DetailedChatFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.detailed_chat_fragment, container, false)

        val application = requireNotNull(this.activity).application

        //Extracting the user id from the navigation argument using safe args
        val arguments = DetailedChatFragmentArgs.fromBundle(arguments!!)
        val userId = arguments.userIdNavigate

        //Database is created when this is called
        val dataSourceDao = LocalDatabase.getInstance(application).chatDatabaseDAO

        val viewModelFactory = DetailedChatViewModelFactory(dataSourceDao, userId, application)

        val detailedChatViewModel= ViewModelProviders.of(
            this, viewModelFactory).get(DetailedChatViewModel::class.java)

        bindingDetailedChat.viewModel = detailedChatViewModel
        bindingDetailedChat.lifecycleOwner = this

        //binding the adapter
        val adapter =  DetailedChatAdapter()
        bindingDetailedChat.detailedChatRecyclerView.adapter = adapter

        //binding the chat messages
        //observer so that we know when the data is updated for chat messages
        //and, assigns value to the adapter data on change
        detailedChatViewModel.allChatMessages.observe(viewLifecycleOwner, Observer {
            it?.let {
                //calling the adapter and notify when the nights object is changed
                //submit list because the adapter inherits from List Adapter
                adapter.submitList(it)

                //scroll to the bottom of the chat - latest messages
                bindingDetailedChat.detailedChatRecyclerView.smoothScrollToPosition(it.size)
            }
        })

        //keeping the Send Message button disabled when the detailed fragment is loaded
        //button will be enabled when the text will be changed on the same using the Text Changed Listener
        val sendMessageButton = bindingDetailedChat.imageButton
        sendMessageButton.isEnabled = false

        //setting the Click Listener on Send Message button
        sendMessageButton.setOnClickListener {
            val userMessage: String = bindingDetailedChat.chatTextBox.text.toString()
            detailedChatViewModel.sendMessageClicked(dataSourceDao,userId, userMessage)

            //empty the text box once message is typed and sent
            bindingDetailedChat.chatTextBox.text = null
        }

        bindingDetailedChat.chatTextBox.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                sendMessageButton.isEnabled = s.toString() != ""
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })

        return bindingDetailedChat.root
    }

    fun disableSendMessageButton(){

    }
}
