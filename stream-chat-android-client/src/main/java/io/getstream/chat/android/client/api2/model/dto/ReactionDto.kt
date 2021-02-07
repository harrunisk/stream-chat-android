package io.getstream.chat.android.client.api2.model.dto

import com.squareup.moshi.JsonClass
import java.util.Date

/**
 * See [io.getstream.chat.android.client.parser2.ReactionDtoAdapter] for
 * special [extraData] handling.
 */
@JsonClass(generateAdapter = true)
internal data class ReactionDto(
    val created_at: Date?,
    val message_id: String,
    val score: Int,
    val type: String,
    val updated_at: Date?,
    val user: UserDto?,
    val user_id: String,

    val extraData: Map<String, Any>,
)
