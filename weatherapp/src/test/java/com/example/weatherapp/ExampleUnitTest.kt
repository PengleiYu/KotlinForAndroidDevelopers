package com.example.weatherapp

import org.junit.Test

import org.junit.Assert.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /**
     * 自定义委托
     */
    private class NotNullSingleValue<T> : ReadWriteProperty<Any?, T> {
        private var value: T? = null
        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return value ?: throw IllegalStateException("${property.name} not initialized")
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            this.value = if (this.value == null) value else
                throw IllegalStateException("${property.name} already initialized")
        }
    }

    private object DelegatesExt {
        fun <T> notNullSingleValue() = NotNullSingleValue<T>()
    }

    @Test
    fun test() {
//        var name by DelegatesExt.notNullSingleValue<String>()
        var name by NotNullSingleValue<String>()
        name = "Hello"
        println(name)
        name = "world"
    }
}
