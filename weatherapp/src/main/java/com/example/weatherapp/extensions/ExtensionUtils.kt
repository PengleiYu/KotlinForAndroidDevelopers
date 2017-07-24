package com.example.weatherapp.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by yupenglei on 17/7/24.
 */
/**
 * Long转日期比较多，所以提取扩展函数
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}
