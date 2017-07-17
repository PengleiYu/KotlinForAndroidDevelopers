package com.example.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        forecast_list.layoutManager = LinearLayoutManager(this)
//        val forecastList = findViewById(R.id.forecast_list) as RecyclerView
        val forecastList: RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)


        val appKey = "28106fa236db9c77bc653ec81f3a8af1"
//        val url = "http://samples.openweathermap.org/data/2.5/weather?q=Beijing&appid=$appKey"
        val url = "http://samples.openweathermap.org/data/2.5/forecast/daily?id=524901&appid=$appKey"
        doAsync {
            Request(url).run()
            uiThread { longToast("Request performed") }
        }
    }
}
