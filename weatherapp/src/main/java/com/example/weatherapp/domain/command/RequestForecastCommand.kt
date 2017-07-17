package com.example.weatherapp.domain.command

import com.example.weatherapp.data.ForecastRequest
import com.example.weatherapp.domain.mappers.ForecastDataMapper
import com.example.weatherapp.domain.model.ForecastList

/**
 * Created by yupenglei on 17/7/17.
 */
class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}
