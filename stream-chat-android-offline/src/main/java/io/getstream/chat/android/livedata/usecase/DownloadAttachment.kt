package io.getstream.chat.android.livedata.usecase

import androidx.annotation.CheckResult
import io.getstream.chat.android.client.call.Call
import io.getstream.chat.android.client.models.Attachment
import io.getstream.chat.android.core.internal.InternalStreamChatApi
import io.getstream.chat.android.livedata.ChatDomain

@InternalStreamChatApi
public sealed interface DownloadAttachment {
    /**
     * Downloads the selected attachment to the "Download" folder in the public external storage directory.
     *
     * @param attachment the attachment to download
     */
    @CheckResult
    public operator fun invoke(attachment: Attachment): Call<Unit>
}

internal class DownloadAttachmentImpl(private val chatDomain: ChatDomain) : DownloadAttachment {
    override operator fun invoke(attachment: Attachment): Call<Unit> =
        chatDomain.downloadAttachment(attachment)
}
