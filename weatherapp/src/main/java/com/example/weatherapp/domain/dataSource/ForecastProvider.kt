package com.example.weatherapp.domain.dataSource

import com.example.weatherapp.data.db.ForecastDb
import com.example.weatherapp.data.server.ForecastServer
import com.example.weatherapp.domain.model.Forecast
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

    /**
     * 按zipCode请求
     */
    fun requestByZipCode(zipCode: Long, days: Int): ForecastList =
            requestToSource {
                val result = it.requestForecastByZipCode(zipCode, todayTimeSpan())
                Logger.d("requestSource: ${it.javaClass.simpleName} => $result\n" +
                        "res.size= ${result?.size()}")
//                if (result != null && result.size() >= days) result else null
                result?.let { if (result.size() >= days) result else null }
            }

    /**
     * 按id查询日天气
     */
    fun reqestDayForecast(id: Long): Forecast = requestToSource { it.requestDayForecast(id) }


    /**
     * 由于两个请求都是遍历集合，并返回第一个非null结果，故抽象出新函数
     */
    private fun <T : Any> requestToSource(f: (ForecastDataSource) -> T?): T =
            sources.firstResult(f)

    private fun todayTimeSpan(): Long =
            System.currentTimeMillis() / day_in_millis * day_in_millis

}