package com.example.weatherapp.extensions

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by JJBOOM on 2017/7/29.
 */
class LongPreference(val context: Context, val name: String, val default: Long) : ReadWriteProperty<Any?, Long> {
    val prefs by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE)!! }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Long = prefs.getLong(name, default)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) = prefs.edit().putLong(name, value).apply()
}

object DelegatesExt {
    fun longPreference(context: Context, name: String, default: Long) = LongPreference(context, name, default)
}