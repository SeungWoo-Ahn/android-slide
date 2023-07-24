package io.softeer.slideapp.util

interface ItemTouchListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
}