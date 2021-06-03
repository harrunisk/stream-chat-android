package io.getstream.chat.android.livedata.usecase

import androidx.annotation.CheckResult
import io.getstream.chat.android.client.call.Call
import io.getstream.chat.android.livedata.ChatDomain

public sealed interface MarkAllRead {
    @CheckResult
    public operator fun invoke(): Call<Boolean>
}

internal data class MarkAllReadImpl(private val chatDomain: ChatDomain) : MarkAllRead {
    override fun invoke(): Call<Boolean> = chatDomain.markAllRead()
}
