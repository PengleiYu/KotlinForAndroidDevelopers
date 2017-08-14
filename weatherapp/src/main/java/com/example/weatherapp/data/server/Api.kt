package com.example.weatherapp.data.server

import com.example.weatherapp.domain.command.RequestForecastCommand
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by yupenglei on 17/8/14.
 */
interface Api {
    companion object {
        private const val AppID = "15646a06818f61f7b8d7823ca833e1ce"
        private val WEATHER_URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=${RequestForecastCommand.DAYS}"
        private val COMPLETE_URL = "$WEATHER_URL&APPID=$AppID&q="
        private const val baseUrl = "http://api.openweathermap.org"
        private const val baseQuery = "mode=json&units=metric&APPID=$AppID&cnt=${RequestForecastCommand.DAYS}"

        val instance: Api by lazy {
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build().create(Api::class.java)
        }
    }

    @GET("data/2.5/forecast/daily?$baseQuery")
    fun queryForcastByZipCode(@Query("q") query: Long):
            Call<ForecastResult>
}
