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

/**
 * 从Y为0处，向上移动到完全隐藏
 */
fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

/**
 * 从上方未完全显示移动到完全显示
 */
fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}