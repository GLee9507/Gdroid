package com.glee.sample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @author liji
 * @date 10/8/2018 1:20 PM
 * description
 */


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}
