package io.softeer.slideapp.util

interface ItemTouchListener {
    fun onItemMove(from_position: Int, to_position: Int): Boolean
}