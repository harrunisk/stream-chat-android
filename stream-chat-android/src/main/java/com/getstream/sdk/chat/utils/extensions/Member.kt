@file:JvmName("MemberUtils")

package com.getstream.sdk.chat.utils.extensions

import io.getstream.chat.android.client.models.Member
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.livedata.ChatDomain.Companion.instance
import java.util.Date

public fun List<Member>.getLastActive(
    currentUser: User = instance().currentUser
): Date {
    return filter { it.user.id != currentUser.id && it.user.lastActive != null }
        .maxOfOrNull { it.user.lastActive!! } ?: Date()
}

@JvmOverloads
public fun List<Member>.getOtherUsers(currentUser: User = instance().currentUser): List<User> {
    return filterNot { it.getUserId() == currentUser.id }
        .map { it.user }
}
