package com.example.weatherapp.data.db

import com.example.weatherapp.domain.dataSource.ForecastDataSource
import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList
import com.example.weatherapp.extensions.*
import com.orhanobut.logger.Logger
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * 用于查询ForecastList和保存ForecastList
 * Created by yupenglei on 17/7/20.
 */
class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    override fun requestDayForecast(id: Long): Forecast? = forecastDbHelper.use {
        val dayForecast = select(DayForecastTable.NAME)
                .byId(id)
                .parseOpt { DayForecast(HashMap(it)) }
//        if (dayForecast != null) dataMapper.convertDayToDomain(dayForecast) else null
        dayForecast?.let { dataMapper.convertDayToDomain(it) }
    }

    /**
     * 通过zipCode查询城市和每日天气，然后合并成ForecastList
     */
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID}={id} and ${DayForecastTable.DATE} >= {date}"
        //查询指定城市的每日天气列表
        val dailyForecast = select(DayForecastTable.NAME)
                .whereArgs(dailyRequest, "id" to zipCode, "date" to date)
//                .parseList(object : MapRowParser<DayForecast> {
//                    override fun parseRow(columns: Map<String, Any?>): DayForecast
//                            = DayForecast(HashMap(columns))
//                })
                .parseList { DayForecast(HashMap(it)) }//使用扩展函数
        //查询指定城市，并构建城市预报对象
        val cityForecast = select(CityForecastTable.NAME)
//                .whereArgs("${CityForecastTable.ID}={id}", "id" to zipCode)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
//                .parseSingle()//该方法若查找失败会抛异常
//                .parseOpt(object : MapRowParser<CityForecast> {
//                    override fun parseRow(columns: Map<String, Any?>): CityForecast
//                            = CityForecast(HashMap(columns), dailyForecast)
//                })
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }//使用扩展函数
        Logger.d("db request: $zipCode,$date => $cityForecast")
//        if (cityForecast != null) dataMapper.convertToDomain(cityForecast) else null
        cityForecast?.let { dataMapper.convertToDomain(cityForecast) }
    }

    /**
     * 保存ForecastList中的城市和每日天气
     */
    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(DayForecastTable.NAME)
        clear(CityForecastTable.NAME)
        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, * map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}
