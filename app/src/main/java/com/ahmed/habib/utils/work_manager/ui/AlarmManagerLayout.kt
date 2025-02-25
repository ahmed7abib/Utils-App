package com.ahmed.habib.utils.work_manager.ui

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context.JOB_SCHEDULER_SERVICE
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import com.ahmed.habib.utils.R
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.ahmed.habib.core.composeable.CommonRoundedButton
import com.ahmed.habib.utils.work_manager.MyJobService
import java.util.Calendar

@Composable
fun WorkManagerLayout() {

    val context = LocalContext.current
    val clockState = rememberSheetState()
    val calenderState = rememberSheetState()

    var timeSelected by remember { mutableStateOf("") }
    var dateSelected by remember { mutableStateOf("") }

    val jobScheduler = context.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

    CalendarDialog(state = calenderState, config = CalendarConfig(
        monthSelection = true, yearSelection = true, style = CalendarStyle.MONTH
    ), selection = CalendarSelection.Date { date ->
        dateSelected = "${date.dayOfMonth}-${date.monthValue}-${date.year}"
    })

    ClockDialog(state = clockState,
        config = ClockConfig(is24HourFormat = false),
        selection = ClockSelection.HoursMinutesSeconds { hours, min, sec ->
            timeSelected = "$hours:$min:$sec"
        })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        CommonRoundedButton(
            name = "Get Date",
            fontSize = 18.sp,
            mainColor = R.color.purple_700,
            textColor = R.color.white,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            calenderState.show()
        }

        CommonRoundedButton(
            name = "Get Time",
            fontSize = 18.sp,
            mainColor = R.color.purple_700,
            textColor = R.color.white,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            clockState.show()
        }

        Text(
            text = "$dateSelected - $timeSelected",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        )

        Divider(
            thickness = 1.dp, modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp)
        )

        Text(
            text = "Schedule event at specific time using Job Service",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 20.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            CommonRoundedButton(
                name = "Schedule Job Service Alarm",
                fontSize = 14.sp,
                mainColor = R.color.purple_200,
                textColor = R.color.white,
                modifier = Modifier.weight(1f)
            ) {
                val componentName = ComponentName(context, MyJobService::class.java)

                val calendar = Calendar.getInstance()
                calendar.set(
                    2023, Calendar.SEPTEMBER, 9, 18, 0, 0
                )

                val delay = calendar.timeInMillis - System.currentTimeMillis()

                val jobInfo = JobInfo.Builder(10, componentName).apply {
                    setMinimumLatency(delay) // if android > N else setPeriodic(delay)
                    setRequiresBatteryNotLow(false)
                    setRequiresDeviceIdle(false)
                    setRequiresStorageNotLow(false)
                    setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    setRequiresCharging(false)
                }.build()

                jobScheduler.schedule(jobInfo)
            }

            CommonRoundedButton(
                name = "Cancel Job Service Alarm",
                fontSize = 14.sp,
                mainColor = R.color.purple_200,
                textColor = R.color.white,
                modifier = Modifier.weight(1f)
            ) {
                jobScheduler.cancelAll()
            }
        }

        Divider(
            thickness = 1.dp, modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp)
        )

        Text(
            text = "Schedule event at specific time using Work Manager",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 20.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            CommonRoundedButton(
                name = "Schedule Work Manager Alarm",
                fontSize = 14.sp,
                mainColor = R.color.purple_200,
                textColor = R.color.white,
                modifier = Modifier.weight(1f)
            ) {

            }

            CommonRoundedButton(
                name = "Cancel Work Manger Alarm",
                fontSize = 14.sp,
                mainColor = R.color.purple_200,
                textColor = R.color.white,
                modifier = Modifier.weight(1f)
            ) {

            }
        }
    }
}