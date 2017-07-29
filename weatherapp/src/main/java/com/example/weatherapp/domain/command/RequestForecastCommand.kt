package com.example.weatherapp.domain.command

import com.example.weatherapp.domain.dataSource.ForecastProvider
import com.example.weatherapp.domain.model.ForecastList

/**
 * Created by yupenglei on 17/7/17.
 */
class RequestForecastCommand(val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider())
    : Command<ForecastList> {

    companion object {
        val DAYS = 15
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}
