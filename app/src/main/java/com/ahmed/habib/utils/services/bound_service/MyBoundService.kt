package com.ahmed.habib.utils.services.bound_service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyBoundService : Service() {

    private var result = 0
    private val binder: BoundServiceBinder = BoundServiceBinder()

    override fun onBind(intent: Intent): IBinder {

        val firstNumber = intent.extras?.getInt("FIRST_NUMBER", -1)
        val secondNumber = intent.extras?.getInt("SECOND_NUMBER", -2)

        if (firstNumber != null && secondNumber != null) {
            val resultNumber = firstNumber + secondNumber
            binder.getService().setResult(resultNumber)
        }

        return binder
    }

    private fun setResult(resultNumber: Int) {
        result = resultNumber
    }

    fun getResult(): Int {
        return result
    }

    override fun onUnbind(intent: Intent): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    inner class BoundServiceBinder : Binder() {
        fun getService(): MyBoundService = this@MyBoundService
    }
}