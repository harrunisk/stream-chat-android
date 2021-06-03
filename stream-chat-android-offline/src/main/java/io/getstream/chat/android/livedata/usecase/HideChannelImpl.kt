package io.getstream.chat.android.livedata.usecase

import androidx.annotation.CheckResult
import io.getstream.chat.android.client.call.Call
import io.getstream.chat.android.livedata.ChatDomain

public sealed interface HideChannel {
    /**
     * Hides the channel with the specified id
     *
     * @param cid: the full channel id i. e. messaging:123
     * @param keepHistory: boolean, if you want to keep the history of this channel or not
     *
     * @see <a href="https://getstream.io/chat/docs/channel_delete/?language=kotlin">Hiding a channel</a>
     */
    @CheckResult
    public operator fun invoke(cid: String, keepHistory: Boolean): Call<Unit>
}

internal class HideChannelImpl(private val chatDomain: ChatDomain) : HideChannel {
    override operator fun invoke(cid: String, keepHistory: Boolean): Call<Unit> =
        chatDomain.hideChannel(cid, keepHistory)
}
