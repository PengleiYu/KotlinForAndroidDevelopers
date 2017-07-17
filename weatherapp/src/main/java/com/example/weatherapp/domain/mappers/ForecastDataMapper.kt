package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.Forecast
import com.example.weatherapp.data.ForecastResult
import com.example.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*

import com.example.weatherapp.domain.model.Forecast as ModelForecast

/**
 * Created by yupenglei on 17/7/17.
 */
class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(),
                generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val dateInstance = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return dateInstance.format(date * 1000)
    }

    private fun generateIconUrl(iconCode: String)
            = "http://openweathermap.org/img/w/$iconCode.png"
}