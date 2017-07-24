package com.example.weatherapp.domain.dataSource

import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList

/**
 * Created by yupenglei on 17/7/20.
 */
interface ForecastDataSource {
    /**
     * 根据日期返回一周的天气
     */
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    /**
     * 按id索引日天气
     */
    fun requestDayForecast(id: Long): Forecast?
}
