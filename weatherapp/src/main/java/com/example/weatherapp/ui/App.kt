package com.example.weatherapp.ui

import android.app.Application
import com.example.weatherapp.ui.utils.DelegateExtension

/**
 * Created by yupenglei on 17/7/18.
 */

class App : Application() {
    companion object {
        //非空单例化
        var instance by DelegateExtension.notNullSingleValue<App>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
