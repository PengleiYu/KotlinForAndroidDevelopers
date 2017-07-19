package com.example.weatherapp.data.db

import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList

/**
 * Created by yupenglei on 17/7/19.
 */
class DbDataMapper {
    fun convertFromDomain(forecastList: ForecastList) = with(forecastList) {
        val daily = forecastList.dailyForecast.map { convertDayFromDomain(forecastList.id, it) }
        CityForecast(forecastList.id, forecastList.city, forecastList.country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }

    fun convertToDomain(cityForecast: CityForecast): ForecastList {
        val daily = cityForecast.dailyForecast.map { convertDayToDomain(it) }
        return ForecastList(cityForecast._id, cityForecast.city, cityForecast.country, daily)
    }

    private fun convertDayToDomain(dayForecast: DayForecast): Forecast {
        return Forecast(dayForecast.date, dayForecast.description, dayForecast.high,
                dayForecast.low, dayForecast.iconUrl)
    }
}
