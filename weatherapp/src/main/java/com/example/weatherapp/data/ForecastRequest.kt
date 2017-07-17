package com.example.weatherapp.data

import com.google.gson.Gson
import java.net.URL


/**
 * Created by yupenglei on 17/7/17.
 */
class ForecastRequest(val zipCode: String) {
    companion object {
        private val AppID = "15646a06818f61f7b8d7823ca833e1ce"
        private val WEATHER_URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$WEATHER_URL&APPID=$AppID&q="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}
