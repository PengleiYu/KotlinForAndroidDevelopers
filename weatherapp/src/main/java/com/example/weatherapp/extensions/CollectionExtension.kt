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
