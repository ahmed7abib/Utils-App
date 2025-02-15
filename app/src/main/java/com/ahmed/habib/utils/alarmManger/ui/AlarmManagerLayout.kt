package com.ahmed.habib.utils.alarmManger.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmed.habib.core.composeable.CommonRoundedButton
import com.ahmed.habib.utils.alarmManger.AlarmModel
import com.ahmed.habib.utils.alarmManger.AndroidIAlarmScheduler

@Composable
fun AlarmManagerLayout() {

    var alarmModelItem: AlarmModel? = null
    val context = LocalContext.current
    val scheduler = AndroidIAlarmScheduler(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center
    ) {

        CommonRoundedButton(
            name = "Schedule Alarm",
            fontSize = 14.sp,
            mainColor = R.color.purple_200,
            textColor = R.color.white
        ) {
            alarmModelItem = AlarmModel(
                time = "08:45:00 PM",
                message = "Hello Ahmed"
            )

            scheduler.schedule(alarmModelItem)
        }

        Spacer(modifier = Modifier.height(8.dp))

        CommonRoundedButton(
            name = "Cancel Alarm",
            fontSize = 14.sp,
            mainColor = R.color.purple_200,
            textColor = R.color.white
        ) {
            scheduler.cancel(alarmModelItem)
        }
    }
}