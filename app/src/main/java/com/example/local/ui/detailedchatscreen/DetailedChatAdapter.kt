package com.example.local.ui.detailedchatscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.local.database.ChatMessage
import com.example.local.database.ChatUser
import com.example.local.databinding.AllChatsItemBinding
import com.example.local.databinding.MyMessageItemRightBinding
import com.example.local.databinding.UserMessageItemLeftBinding


class DetailedChatAdapter():
    ListAdapter<ChatMessage, DetailedChatAdapter.ViewHolderUserMessage>(ChatMessageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUserMessage {

        //Right Side Message of the Mine
        return if (viewType == 1) {
            val viewRoot = LayoutInflater.from(parent.context)
                .inflate(com.example.local.R.layout.my_message_item_right, parent, false)
            ViewHolderUserMessage(viewRoot)
        } else {
            val viewRoot = LayoutInflater.from(parent.context)
                .inflate(com.example.local.R.layout.user_message_item_left, parent, false)
            ViewHolderUserMessage(viewRoot)
        }
    }

    //On binding the view holder with the adapter
    //Responsible for binding the data to each UI view
    //Calls every time and for each item (using position) in recycler view
    override fun onBindViewHolder(holder: ViewHolderUserMessage, position: Int) {


        val item = getItem(position)

        //Binding the click listener and item to the holder
        holder.bind(item)
    }

    //Custom View Holder Class to hold Custom Item View Layout
    class ViewHolderUserMessage (view: View)
            : RecyclerView.ViewHolder(view) {

            fun bind(item: ChatMessage) {

                if(item.messageInwardFlow) {

                    val binding = DataBindingUtil.bind<UserMessageItemLeftBinding>(itemView)

                    binding?.chatMessage = item

                    //this by let binding adapters (bindingutils.kt) perform the actual view bindings
                    binding?.executePendingBindings()

                }
                else {

                    val binding = DataBindingUtil.bind<MyMessageItemRightBinding>(itemView)

                    binding?.chatMessage = item

                    //this by let binding adapters (bindingutils.kt) perform the actual view bindings
                    binding?.executePendingBindings()
                }

        }

    }

    //To return on which item view to inflate - user or mine
    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)

        return if(item.messageInwardFlow)
            0
        else 1
    }


}

/** Need custom DiffUtil class and related functions so that it can help upgrade the Recycler View
more efficiently than notifyDataSetChanged() method which binds the whole view of recycler again
leads to expensive and slow UI
The effective way to do is to use DiffUtil class in the adapter that determines which item
to change instead of re-binding the whole recycler view /
This function needs to be passed in the constructor of the Adapter so that adapter knows when to
update the view efficiently */
class ChatMessageDiffCallback : DiffUtil.ItemCallback<ChatMessage>() {
    override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
        //how to check if two items are same - sleep nights using their unique id or primary key
        return oldItem.messageId == newItem.messageId
    }

    override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
        //how to check if two items are same from content perspective
        // the below equation works as this is allowed in the data class for Chat Message object
        return oldItem == newItem
    }
}

