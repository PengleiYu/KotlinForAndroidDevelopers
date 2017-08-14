package com.example.weatherapp

import com.example.weatherapp.data.server.Api
import org.junit.Test

/**
 * Created by yupenglei on 17/8/14.
 */
class NetUnitTest {
    @Test
    fun testForecastRequest() {
        val execute = Api.instance.queryForcastByZipCode(123445).execute()
        println(execute.body())
    }
}
