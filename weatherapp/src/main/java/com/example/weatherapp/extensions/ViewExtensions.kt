package com.example.weatherapp.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * Created by yupenglei on 17/7/18.
 */

/**
 * 用于和Activity、Fragment行为保持一致
 */
val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)