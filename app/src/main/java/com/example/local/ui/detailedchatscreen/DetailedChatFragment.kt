package com.example.local.ui.detailedchatscreen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.local.R

class DetailedChatFragment : Fragment() {

    companion object {
        fun newInstance() = DetailedChatFragment()
    }

    private lateinit var viewModel: DetailedChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detailed_chat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailedChatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
