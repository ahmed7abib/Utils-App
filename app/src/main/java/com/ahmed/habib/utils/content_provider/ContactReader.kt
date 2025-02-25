package com.ahmed.habib.utils.content_provider

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract

@SuppressLint("Range", "Recycle")
object ContactReader {

    private var contactList: ArrayList<ContactModel> = ArrayList()

    fun getContacts(context: Context): List<ContactModel> {

        val allNamesCursor = context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME + " ASC"
        )

        if (allNamesCursor?.moveToFirst() == true) {
            do {
                val id = allNamesCursor.getLong(allNamesCursor.getColumnIndex("_ID"))

                val singleNameByIdCursor = context.contentResolver.query(
                    ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + "=?",
                    arrayOf(id.toString()),
                    null
                )

                var displayName: String
                var nickName: String

                var homePhone: String
                var mobilePhone: String
                var workPhone: String

                var homeEmail: String
                var workEmail: String

                var companyName: String
                var title: String
                var contactOtherDetails = ""

                if (singleNameByIdCursor?.moveToFirst() == true) {

                    displayName = getDisplayName(singleNameByIdCursor) ?: ""
                    contactOtherDetails += "Display Name $displayName \n"

                    do {
                        nickName = getNickName(singleNameByIdCursor) ?: ""
                        contactOtherDetails += "NickName $nickName \n"

                        homePhone = getHomePhone(singleNameByIdCursor) ?: ""
                        contactOtherDetails += "Home Phone $homePhone \n"

                        workPhone = getWorkPhone(singleNameByIdCursor) ?: ""
                        contactOtherDetails += "Work Phone $workPhone \n"

                        mobilePhone = getMobilePhone(singleNameByIdCursor) ?: ""
                        contactOtherDetails += "Mobile Phone $mobilePhone \n"

                        homeEmail = getHomeEmail(singleNameByIdCursor) ?: ""
                        contactOtherDetails += "Home Email $homeEmail \n"

                        workEmail = getWorkEmail(singleNameByIdCursor) ?: ""
                        contactOtherDetails += "Work Email $workEmail \n"

                        companyName = getCompanyName(singleNameByIdCursor) ?: ""
                        contactOtherDetails += "Company Name $companyName \n"

                        title = getTitle(singleNameByIdCursor) ?: ""
                        contactOtherDetails += " Title $title \n"

                    } while (singleNameByIdCursor.moveToNext())

                    contactList.add(
                        ContactModel(
                            id,
                            displayName,
                            nickName,
                            homePhone,
                            workPhone,
                            mobilePhone,
                            homeEmail,
                            workEmail,
                            null,
                            companyName,
                            title,
                            contactOtherDetails
                        )
                    )
                }

            } while (allNamesCursor.moveToNext())
        }

        return contactList
    }

    private fun getDisplayName(singleNameByIdCursor: Cursor): String? {
        return singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
    }

    private fun getNickName(singleNameByIdCursor: Cursor): String? {
        if (singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("mimetype"))
                .equals(ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)
        ) {
            return singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("data1"))
        }

        return null
    }

    private fun getHomePhone(singleNameByIdCursor: Cursor): String? {
        val mimeType =
            singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("mimetype"))

        if (mimeType.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {

            val data2Column =
                singleNameByIdCursor.getInt(singleNameByIdCursor.getColumnIndex("data2"))

            if (data2Column == ContactsContract.CommonDataKinds.Phone.TYPE_HOME) {
                return singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("data1"))
            }
        }

        return null
    }

    private fun getWorkPhone(singleNameByIdCursor: Cursor): String? {
        val mimeType =
            singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("mimetype"))

        if (mimeType.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {

            val data2Column =
                singleNameByIdCursor.getInt(singleNameByIdCursor.getColumnIndex("data2"))

            if (data2Column == ContactsContract.CommonDataKinds.Phone.TYPE_WORK) {
                return singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("data1"))
            }
        }

        return null
    }

    private fun getMobilePhone(singleNameByIdCursor: Cursor): String? {
        val mimeType =
            singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("mimetype"))

        if (mimeType.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {

            val data2Column =
                singleNameByIdCursor.getInt(singleNameByIdCursor.getColumnIndex("data2"))

            if (data2Column == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE) {
                return singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("data1"))
            }
        }

        return null
    }

    private fun getHomeEmail(singleNameByIdCursor: Cursor): String? {
        val mimeType =
            singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("mimetype"))

        if (mimeType.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {

            val data2Column =
                singleNameByIdCursor.getInt(singleNameByIdCursor.getColumnIndex("data2"))

            if (data2Column == ContactsContract.CommonDataKinds.Email.TYPE_HOME) {
                return singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("data1"))
            }
        }

        return null
    }

    private fun getWorkEmail(singleNameByIdCursor: Cursor): String? {
        val mimeType =
            singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("mimetype"))

        if (mimeType.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {

            val data2Column =
                singleNameByIdCursor.getInt(singleNameByIdCursor.getColumnIndex("data2"))

            if (data2Column == ContactsContract.CommonDataKinds.Email.TYPE_WORK) {
                return singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("data1"))
            }
        }

        return null
    }

    private fun getCompanyName(singleNameByIdCursor: Cursor): String? {
        val mimeType =
            singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("mimetype"))

        if (mimeType.equals(ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)) {
            return singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("data1"))
        }

        return null
    }

    private fun getTitle(singleNameByIdCursor: Cursor): String? {
        val mimeType =
            singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("mimetype"))

        if (mimeType.equals(ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)) {
            return singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("data4"))
        }

        return null
    }

    private fun getPhoto(singleNameByIdCursor: Cursor): ByteArray? {
        val mimeType =
            singleNameByIdCursor.getString(singleNameByIdCursor.getColumnIndex("mimetype"))

        if (mimeType.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
            return singleNameByIdCursor.getBlob(singleNameByIdCursor.getColumnIndex("data15"))
        }

        return null
    }
}