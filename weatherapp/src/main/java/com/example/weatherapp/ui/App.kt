package com.example.weatherapp.ui

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by yupenglei on 17/7/18.
 */

class App : Application() {
    companion object {
        //        private var instance:Application?=null
//        fun instance()= instance!!
        var instance: App by Delegates.notNull<App>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
