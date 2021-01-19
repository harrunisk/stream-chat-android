package com.getstream.sdk.chat.adapter.viewholder.message

import android.view.ViewGroup
import com.getstream.sdk.chat.adapter.MessageListItem.ThreadSeparatorItem
import com.getstream.sdk.chat.adapter.MessageListItemPayloadDiff
import com.getstream.sdk.chat.databinding.StreamItemThreadSeparatorBinding
import com.getstream.sdk.chat.utils.extensions.inflater

class ThreadSeparatorViewHolder(
    parent: ViewGroup,
    binding: StreamItemThreadSeparatorBinding =
        StreamItemThreadSeparatorBinding.inflate(parent.inflater, parent, false)
) : BaseMessageListItemViewHolder<ThreadSeparatorItem>(binding.root) {

    override fun bind(messageListItem: ThreadSeparatorItem, diff: MessageListItemPayloadDiff) {
        /* Empty */
    }
}
