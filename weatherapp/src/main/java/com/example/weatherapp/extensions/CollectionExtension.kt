package com.example.weatherapp.extensions

/**
 * Created by yupenglei on 17/7/20.
 */

/**
 * 将map转为键值对数组
 */
fun <K, V> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> {
    return map { it.key to it.value!! }.toTypedArray()
}

fun <T, R : Any> Iterable<T>.firstResult(predicate: (T) -> R?): R {
    forEach {
        val result = predicate(it)
        if (result != null) return result
    }
//    mapNotNull { predicate(it) }.forEach { return it }
    throw NoSuchElementException("No element matching predicate was found")
}