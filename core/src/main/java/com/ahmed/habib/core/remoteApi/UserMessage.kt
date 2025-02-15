package com.ahmed.habib.core.remoteApi

import android.content.Context

data class UserMessage(
    private val resMessage: Int? = null,
    private val strMessage: String? = null
) {
    fun getMessage(context: Context): String? {
        return if (resMessage != null) {
            context.getString(resMessage)
        } else {
            strMessage
        }
    }
}