package io.softeer.slideapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import io.softeer.slideapp.R
import io.softeer.slideapp.data.local.RGBColor

class DrawingView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private val path = Path()

    init {
        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.DrawingView,
            0,0
        )?.apply {
            try {

            } finally {
                recycle()
            }
        }
    }

    private fun setDefaultColor(color: RGBColor) {
        paint.setARGB(255, color.r,color.g, color.b)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return true
        val x = event.x
        val y = event.y
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
            }
            MotionEvent.ACTION_UP -> {
                path.lineTo(x, y)
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
    }
}