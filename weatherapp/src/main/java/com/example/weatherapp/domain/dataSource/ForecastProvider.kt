package com.example.weatherapp.domain.dataSource

import com.example.weatherapp.data.db.ForecastDb
import com.example.weatherapp.data.server.ForecastServer
import com.example.weatherapp.domain.model.ForecastList
import com.example.weatherapp.extensions.firstResult
import com.orhanobut.logger.Logger

/**
 * Created by yupenglei on 17/7/20.
 */
class ForecastProvider(val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {
    companion object {
        val day_in_millis = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList =
            sources.firstResult { requestSource(it, days, zipCode) }

    fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        Logger.d("requestSource: ${source.javaClass.simpleName} => $res\n" +
                "res.size= ${res?.size()}")
        return if (res != null && res.size() >= days) res else null
    }

    private fun todayTimeSpan(): Long =
            System.currentTimeMillis() / day_in_millis * day_in_millis

}