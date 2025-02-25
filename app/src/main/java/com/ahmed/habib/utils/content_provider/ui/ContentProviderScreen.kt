package com.ahmed.habib.utils.content_provider.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ahmed.habib.core.utils.showToast
import com.ahmed.habib.utils.content_provider.ContactModel
import com.ahmed.habib.utils.content_provider.ContactReader

@Composable
fun ContentProviderScreen() {

    val context = LocalContext.current
    var contactList by remember { mutableStateOf(listOf<ContactModel>()) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            contactList = ContactReader.getContacts(context)
        } else {
            context.showToast("Permission not permitted.")
        }
    }

    LaunchedEffect(true) {
        permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
    }

    Column(modifier = Modifier.padding(20.dp)) {
        LazyColumn {
            itemsIndexed(contactList) { _, item ->

                Card(modifier = Modifier.padding(10.dp)) {

                    Column(modifier = Modifier.padding(4.dp)) {

                        Row(modifier = Modifier.padding(4.dp)) {

                            if (item.photo != null) {
                                AsyncImage(
                                    model = item.photo,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(0.dp)
                                        .weight(2f)
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .width(0.dp)
                                    .weight(8f)
                                    .padding(start = 4.dp)
                            ) {
                                Text(text = item.displayName)

                                val phoneNum = if (item.homePhone.isNotEmpty()) {
                                    item.homePhone
                                } else if (item.mobilePhone.isNotEmpty()) {
                                    item.mobilePhone
                                } else {
                                    item.workPhone
                                }

                                Text(text = phoneNum, modifier = Modifier.padding(top = 6.dp))
                            }
                        }

                        Divider(
                            modifier = Modifier
                                .height(1.dp)
                                .padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}