package com.ahmed.habib.utils.alarm_manger

interface IAlarmScheduler {
    fun schedule(item: AlarmModel?)
    fun cancel(item: AlarmModel?)
}