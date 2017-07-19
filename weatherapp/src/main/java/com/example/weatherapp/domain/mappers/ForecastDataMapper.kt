package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.server.ForecastRaw
import com.example.weatherapp.data.server.ForecastResult
import com.example.weatherapp.domain.model.ForecastList
import com.example.weatherapp.domain.model.Forecast

/**
 * Created by yupenglei on 17/7/17.
 */
class ForecastDataMapper {
    fun convertFromDataModel(zipCode: Long, forecast: ForecastResult): ForecastList {
        return ForecastList(zipCode, forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<ForecastRaw>): List<Forecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: ForecastRaw): Forecast {
        return Forecast(forecast.dt, forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(),
                generateIconUrl(forecast.weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String)
            = "http://openweathermap.org/img/w/$iconCode.png"
}