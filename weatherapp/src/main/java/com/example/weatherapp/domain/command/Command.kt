package com.example.weatherapp.domain.command

/**
 * Created by yupenglei on 17/7/17.
 */

interface Command<out T> {
    fun execute(): T
}
