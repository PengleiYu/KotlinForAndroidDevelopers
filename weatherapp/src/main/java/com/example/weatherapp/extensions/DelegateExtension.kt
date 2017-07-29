package com.example.weatherapp.extensions

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by yupenglei on 17/7/18.
 */
object DelegateExtension {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
    fun longPreference(context: Context, name: String, default: Long) = LongPreference(context, name, default)
}

class NotNullSingleValueVar<T> : ReadWriteProperty<Any?, T> {
    private var mValue: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return mValue ?: throw IllegalStateException("${property.name} not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        mValue = if (mValue == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}
class LongPreference(val context: Context, val name: String, val default: Long) : ReadWriteProperty<Any?, Long> {
    val prefs by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE)!! }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Long = prefs.getLong(name, default)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) = prefs.edit().putLong(name, value).apply()
}
