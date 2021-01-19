package com.getstream.sdk.chat.utils.extensions

import java.util.Date

fun Date.isInLastMinute(): Boolean = (Date().time - 60000 < time)
