package com.example.local.ui.allchatsscreen

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.local.R
import com.example.local.database.ChatUser

//Binding the App level Parameters to the Data Entity Object called Chat User Class
//These bindings (and parameter item) are coming from the Chat User Adapter
//And, these are being used in the various layouts and views like all_chats_item.xml layout view (refer
//app parameters in the views)
@BindingAdapter("chatUserImage")
fun ImageView.chatUserImage(item: ChatUser?) {
    item?.let {
        //In case image is empty set it to the Default image
        if (item.userImage == "default") {
            setImageResource(R.drawable.ic_user)
        } else setImageURI(Uri.parse(item.userImage))
    }
}

@BindingAdapter("chatUserName")
fun TextView.chatUserName(item: ChatUser?) {
    item?.let {
        text = item.userName
    }
}

@BindingAdapter("chatUserLastMessage")
fun TextView.chatUserLastMessage(item: ChatUser?) {
    item?.let {
        text = item.lastMessage
    }
}