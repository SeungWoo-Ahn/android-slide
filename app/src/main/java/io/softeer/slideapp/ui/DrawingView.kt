package io.softeer.slideapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import io.softeer.slideapp.data.local.Point
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.max
import kotlin.math.min

class DrawingView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private val path = Path()
    private val borderPaint = Paint()
    private val borderRect = Rect()
    private val pointedList = mutableListOf<Point>()
    private lateinit var viewModel: SlideViewModel


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return true
        val x = event.x
        val y = event.y
        if (!viewModel.slideEditable.value) {
            if (event.action == MotionEvent.ACTION_DOWN) {
                val borderSize = calculateRect()
                if (isInBorderBox(x.toInt(), y.toInt(), borderSize) && !viewModel.slideSelect.value) {
                    viewModel.changeSlideStatus(true)
                    borderRect.set(borderSize[0], borderSize[1], borderSize[2], borderSize[3])
                    invalidate()
                }
                if (!isInBorderBox(x.toInt(), y.toInt(), borderSize) && viewModel.slideSelect.value) {
                    viewModel.changeSlideStatus(false)
                    borderRect.setEmpty()
                    invalidate()
                }
            }
            return true
        }
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                pointedList.add(Point(x, y))
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                pointedList.add(Point(x, y))
            }
            MotionEvent.ACTION_UP -> {
                path.lineTo(x, y)
                viewModel.saveSlidePoints(pointedList)
                val borderSize = calculateRect()
                borderRect.set(borderSize[0], borderSize[1], borderSize[2], borderSize[3])
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setToDraw()
        canvas?.let {
            it.drawPath(path, paint)
            it.drawRect(borderRect, borderPaint)
        }
    }

    private fun setToDraw() {
        val strokeColor = "#${viewModel.slideHexColor.value}"
        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = 5f
            color = Color.parseColor(strokeColor)
        }
        borderPaint.apply {
            style = Paint.Style.STROKE
            strokeWidth = 5f
            color = Color.RED
        }
    }

    private fun calculateRect(): List<Int> {
        var minX = pointedList[0].x
        var maxX = pointedList[0].x
        var minY = pointedList[0].y
        var maxY = pointedList[0].y
        for (point in pointedList) {
            minX = min(minX, point.x)
            minY = min(minY, point.y)
            maxX = max(maxX, point.x)
            maxY = max(maxY, point.y)
        }
        return listOf(minX.toInt(), maxY.toInt(), maxX.toInt(), minY.toInt())
    }

    private fun isInBorderBox(x: Int, y: Int, borderSize: List<Int>): Boolean {
        return x >= borderSize[0] && y <= borderSize[1] && x <= borderSize[2] && y >= borderSize[3]
    }

    fun setViewModel(viewModel: SlideViewModel) {
        this.viewModel = viewModel
    }
}