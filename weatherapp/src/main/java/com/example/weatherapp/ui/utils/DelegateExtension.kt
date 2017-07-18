package com.example.weatherapp.ui.utils

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by yupenglei on 17/7/18.
 */
object DelegateExtension {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
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

