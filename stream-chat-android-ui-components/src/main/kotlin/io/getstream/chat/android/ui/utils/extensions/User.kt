package io.getstream.chat.android.ui.utils.extensions

import android.content.Context
import android.text.format.DateUtils
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.client.models.name
import io.getstream.chat.android.livedata.ChatDomain
import io.getstream.chat.android.ui.R

internal fun List<User>.withoutCurrentUser(): List<User> {
    return if (ChatDomain.isInitialized) {
        val currentUser = ChatDomain.instance().currentUser
        filter { it.id != currentUser.id }
    } else {
        this
    }
}

internal fun User.isCurrentUser(): Boolean {
    return if (ChatDomain.isInitialized) {
        id == ChatDomain.instance().currentUser.id
    } else {
        false
    }
}

public fun User.getLastSeenText(context: Context): String {
    return if (online) {
        context.getString(R.string.stream_ui_message_list_header_online)
    } else {
        val lastActive = lastActive ?: return String.EMPTY
        context.getString(
            R.string.stream_ui_message_list_header_last_seen,
            DateUtils.getRelativeTimeSpanString(lastActive.time).toString()
        )
    }
}

internal fun User.asMention(context: Context): String =
    context.getString(R.string.stream_ui_mention_user_name_template, name)
