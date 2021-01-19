package com.getstream.sdk.chat.utils.extensions

inline fun Boolean.whenTrue(crossinline f: () -> Unit): Boolean = also { if (this) f() }
inline fun Boolean.whenFalse(crossinline f: () -> Unit): Boolean = also { if (!this) f() }
