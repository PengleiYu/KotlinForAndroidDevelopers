package com.example.weatherapp.data.server

import android.util.Log
import com.google.gson.Gson
import java.net.URL


/**
 * Created by yupenglei on 17/7/17.
 */
class ForecastRequest(val zipCode: Long) {
    companion object {
        private val AppID = "15646a06818f61f7b8d7823ca833e1ce"
        private val WEATHER_URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "${com.example.weatherapp.data.server.ForecastRequest.Companion.WEATHER_URL}&APPID=${com.example.weatherapp.data.server.ForecastRequest.Companion.AppID}&q="
    }

    fun execute(): ForecastResult {
        val url = COMPLETE_URL + zipCode
        Log.d(javaClass.simpleName, url)
        val forecastJsonStr = URL(url).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}
