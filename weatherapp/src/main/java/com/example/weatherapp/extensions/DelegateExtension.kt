package com.example.weatherapp.extensions

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by yupenglei on 17/7/18.
 */
object DelegateExtension {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
//    fun longPreference(context: Context, name: String, default: Long) = LongPreference(context, name, default)
    fun <T> preference(context: Context, name: String, default: T) = Preference(context, name, default)
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

class Preference<T>(val context: Context, val name: String, val default: T) : ReadWriteProperty<Any?, T> {
    val prefs by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE)!! }
    override fun getValue(thisRef: Any?, property: KProperty<*>): T = findPreference(name, default)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putPreference(name, value)

    private fun <T> findPreference(name: String, default: T): T = with(prefs) {
        val result: Any = when (default) {
            is String -> getString(name, default)
            is Boolean -> getBoolean(name, default)
            is Int -> getInt(name, default)
            is Long -> getLong(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException()
        }
        @Suppress("unchecked_cast")
        result as T
    }

    /**
     * 若使用with，则studio无法识别editor的关闭
     */
    private fun <T> putPreference(name: String, value: T) = prefs.edit().apply {
        when (value) {
            is String -> putString(name, value)
            is Boolean -> putBoolean(name, value)
            is Int -> putInt(name, value)
            is Long -> putLong(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException()
        }
    }.apply()
}