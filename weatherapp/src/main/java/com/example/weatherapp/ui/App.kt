package com.example.weatherapp.ui

import android.app.Application

/**
 * Created by yupenglei on 17/7/18.
 */

class App:Application(){
    companion object{
        private var instance:Application?=null
        fun instance()= instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}
