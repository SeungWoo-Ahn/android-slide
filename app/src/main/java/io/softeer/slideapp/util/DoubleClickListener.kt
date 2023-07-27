package io.softeer.slideapp.util

import android.view.View
import android.view.View.OnClickListener

abstract class DoubleClickListener : OnClickListener {

    private var lastClickTime : Long = 0L

    override fun onClick(v: View?) {
        onOneClick(v)
        val clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
            onDoubleClick(v)
        }
        lastClickTime = clickTime
    }

    abstract fun onOneClick(v: View?)
    abstract fun onDoubleClick(v: View?)

    companion object {
        private const val DOUBLE_CLICK_TIME_DELTA: Long = 300
    }
}