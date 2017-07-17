package com.example.weatherapp

import android.util.Log
import java.net.URL
import java.util.*

/**
 * Created by yupenglei on 17/7/17.
 */
class Request(val url: String) {
    fun run() {
        /*
        打开url，并读取数据
         */
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}

data class Forecast(val date: Date, val temperature: Float, val details: String)