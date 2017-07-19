package com.example.weatherapp.data.server


/**
 * Created by yupenglei on 17/7/17.
 */
class ForecastRequest(val zipCode: String) {
    companion object {
        private val AppID = "15646a06818f61f7b8d7823ca833e1ce"
        private val WEATHER_URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "${com.example.weatherapp.data.server.ForecastRequest.Companion.WEATHER_URL}&APPID=${com.example.weatherapp.data.server.ForecastRequest.Companion.AppID}&q="
    }

    fun execute(): com.example.weatherapp.data.ForecastResult {
        val url = com.example.weatherapp.data.server.ForecastRequest.Companion.COMPLETE_URL + zipCode
        android.util.Log.d(javaClass.simpleName, url)
        val forecastJsonStr = java.net.URL(url).readText()
        return com.google.gson.Gson().fromJson(forecastJsonStr, com.example.weatherapp.data.ForecastResult::class.java)
    }
}
