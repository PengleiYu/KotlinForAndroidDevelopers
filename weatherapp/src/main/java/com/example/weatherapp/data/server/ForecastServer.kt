package com.example.weatherapp.data.server

import com.example.weatherapp.data.db.ForecastDb
import com.example.weatherapp.domain.dataSource.ForecastDataSource
import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList
import com.orhanobut.logger.Logger

/**
 * Created by yupenglei on 17/7/20.
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        //请求网络
        val result: ForecastResult = ForecastByZipCodeRequest(zipCode).execute()
        Logger.d("internet request: $zipCode,$date => $result")
        //转换为待显示的数据
        val forecastList: ForecastList = dataMapper.convertFromDataModel(zipCode, result)
        //保存到数据库
        forecastDb.saveForecast(forecastList)
        //从数据库查询
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    /**
     * 日天气一定能从数据库查出
     */
    override fun requestDayForecast(id: Long): Forecast? {
        throw UnsupportedOperationException()
    }
}
