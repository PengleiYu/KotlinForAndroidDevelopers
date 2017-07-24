package com.example.weatherapp.domain.command

import com.example.weatherapp.domain.dataSource.ForecastProvider
import com.example.weatherapp.domain.model.Forecast

/**
 * 日天气请求命令
 * Created by yupenglei on 17/7/24.
 */
class RequestDayForecastCommand(val id: Long,
                                val forecastProvider: ForecastProvider = ForecastProvider())
    : Command<Forecast> {
    override fun execute(): Forecast = forecastProvider.reqestDayForecast(id)
}