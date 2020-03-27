package io.getstream.chat.android.client.models

import com.google.gson.annotations.SerializedName
import io.getstream.chat.android.client.api.models.CustomObject
import io.getstream.chat.android.client.parser.IgnoreDeserialisation
import io.getstream.chat.android.client.parser.IgnoreSerialisation
import java.util.*


data class Reaction(
    @SerializedName("message_id")
    val messageId: String,
    val type: String,
    val score: Int
) : CustomObject {

    var user: User? = null

    @SerializedName("user_id")
    var userId: String = ""
    @SerializedName("created_at")
    val createdAt: Date? = null

    @IgnoreSerialisation
    @IgnoreDeserialisation
    override var extraData = mutableMapOf<String, Any>()
}