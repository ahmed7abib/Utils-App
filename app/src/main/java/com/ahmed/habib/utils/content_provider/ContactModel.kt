package com.ahmed.habib.utils.content_provider

data class ContactModel(
    val id: Long,
    val displayName: String,
    val nickName: String,
    val workPhone: String,
    val homePhone: String,
    val mobilePhone: String,
    val homeEmail: String,
    val workEmail: String,
    val photo: String?,
    val company: String,
    val title: String,
    val otherDetails: String
)