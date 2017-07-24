package com.example.weatherapp.data.server

import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by yupenglei on 17/7/17.
 */
class ServerDataMapper {
    fun convertFromDataModel(zipCode: Long, forecast: ForecastResult): ForecastList {
        return ForecastList(zipCode, forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<ForecastRaw>): List<Forecast> {
        return list.mapIndexed { index, forecastRaw ->
            val time = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastItemToDomain(forecastRaw.copy(dt = time))
        }
    }

    private fun convertForecastItemToDomain(forecast: ForecastRaw): Forecast = with(forecast) {
        Forecast(-1, dt, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String)
            = "http://openweathermap.org/img/w/$iconCode.png"
}