package com.example.weatherapp.data.db

import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList

/**
 * Created by yupenglei on 17/7/19.
 */
class DbDataMapper {
    fun convertFromDomain(forecastList: ForecastList) = with(forecastList) {

    }

    private fun convertDayFromDomain(forecast: Forecast) = with(forecast) {
//        DayForecast(date, description, high, low, iconUrl)
    }
}
