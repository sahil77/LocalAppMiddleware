package com.example.local.ui.allchatsscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.local.database.ChatUser
import com.example.local.databinding.AllChatsItemBinding


class ChatUserAdapter(val clickListener: ChatUserListener):
    ListAdapter<ChatUser, ChatUserAdapter.ViewHolder>(ChatUserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //Calling Companion Function - 'from' inside the ViewHolder Class
        return ViewHolder.from(parent)
    }

    //On binding the view holder with the adapter
    //Responsible for binding the data to each UI view
    //Calls every time and for each item (using position) in recycler view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        //Binding the click listener and item to the holder
        holder.bind(clickListener, item)
    }

    //Custom View Holder Class to hold Custom Item View Layout
    class ViewHolder private constructor(val binding: AllChatsItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: ChatUserListener, item: ChatUser) {

            //binding the item to the data view directly
            binding.chatUser = item

            //Actual Binding of the Click Listener with the list item view
            binding.clickChatUserListener = clickListener

            //this by let binding adapters (bindingutils.kt) perform the actual view bindings
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)

                //Data Binding is done with List Item View from XML
                //Check the XML and Data Variable
                //AllChatsItemBinding class is created with following steps:
                // 1. Convert the List Item View (XML) as Data Binding View
                // 2. Rebuild the project and this should create the Data Binding Class
                val binding = AllChatsItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


}

/** Need custom DiffUtil class and related functions so that it can help upgrade the Recycler View
    more efficiently than notifyDataSetChanged() method which binds the whole view of recycler again
    leads to expensive and slow UI
    The effective way to do is to use DiffUtil class in the adapter that determines which item
    to change instead of re-binding the whole recycler view /
    This function needs to be passed in the constructor of the Adapter so that adapter knows when to
    update the view efficiently */
class ChatUserDiffCallback : DiffUtil.ItemCallback<ChatUser>() {
    override fun areItemsTheSame(oldItem: ChatUser, newItem: ChatUser): Boolean {
        //how to check if two items are same - sleep nights using their unique id or primary key
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: ChatUser, newItem: ChatUser): Boolean {
        //how to check if two items are same from content perspective
        // the below equation works as this is allowed in the data class for Chat User object
        return oldItem == newItem
    }
}

/** The class to define the click listener for the list item in the Recycler View **/
class ChatUserListener(val clickListener: (userId: Long) -> Unit) {
    fun onClick(chatUser: ChatUser) = clickListener(chatUser.userId)
}
