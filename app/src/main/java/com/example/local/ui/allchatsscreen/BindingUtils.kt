package com.example.local.ui.allchatsscreen

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.local.database.ChatUser

//Binding the App level Parameters to the Data Entity Object called Chat User Class
//These bindings (and parameter item) are coming from the Chat User Adapter
//And, these are being used in the various layouts and views like all_chats_item.xml layout view (refer
//app parameters in the views)
@BindingAdapter("chatUserImage")
fun ImageView.setUserImage(item: ChatUser?) {
    item?.let {
        setImageBitmap(item.userImage)
    }
}

@BindingAdapter("chatUserName")
fun TextView.setSleepDurationFormatted(item: ChatUser?) {
    item?.let {
        text = item.userName
    }
}

@BindingAdapter("chatUserLastMessage")
fun TextView.setSleepQualityString(item: ChatUser?) {
    item?.let {
        text = item.lastMessage
    }
}