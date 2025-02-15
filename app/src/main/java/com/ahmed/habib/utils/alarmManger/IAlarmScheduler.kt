package com.ahmed.habib.utils.alarmManger

interface IAlarmScheduler {
    fun schedule(item: AlarmModel?)
    fun cancel(item: AlarmModel?)
}