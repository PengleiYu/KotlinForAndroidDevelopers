package com.example.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.domain.command.RequestForecastCommand
import com.example.weatherapp.ui.adapters.ForecastListAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

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

//        val forecastList: RecyclerView = find(R.id.forecast_list)
        forecast_list.layoutManager = LinearLayoutManager(this)
        doAsync {
            test()
            val result = RequestForecastCommand(94043).execute()
            Logger.d(result.toString())
            uiThread {
                forecast_list.adapter = ForecastListAdapter(result) { toast("Hello${it.date}") }
                longToast("ForecastRequest performed")
            }
        }
    }

    private fun test() {
        val readText = URL("http://www.baidu.com").readText()
        Logger.d(readText)
    }
}
