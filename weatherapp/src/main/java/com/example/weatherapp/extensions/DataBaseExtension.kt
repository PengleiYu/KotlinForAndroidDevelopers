package com.example.weatherapp.extensions

import android.database.sqlite.SQLiteDatabase
import com.orhanobut.logger.Logger
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

/**
 * Created by yupenglei on 17/7/20.
 */

/**
 * parser是一个将map转为需求对象的lambda
 * 调用parseList函数，传入一个自定义的MapRowParser，对其columns调用lambda
 */
fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T): List<T> =
        parseList(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T {
                Logger.e(columns.toString())
                return parser(columns)
            }
        })

/**
 * parser是一个将map转为需求对象的lambda
 * 调用parseOpt函数，传入一个自定义的MapRowParser，对其columns调用lambda
 */
fun <T : Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T): T? =
        parseOpt(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })

fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}
