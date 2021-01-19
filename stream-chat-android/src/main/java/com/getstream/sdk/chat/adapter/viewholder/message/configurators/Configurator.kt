package com.getstream.sdk.chat.adapter.viewholder.message.configurators

import com.getstream.sdk.chat.adapter.MessageListItem

interface Configurator {
    fun configure(messageItem: MessageListItem.MessageItem)
}
