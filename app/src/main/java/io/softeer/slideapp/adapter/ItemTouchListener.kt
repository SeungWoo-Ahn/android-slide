package io.softeer.slideapp.adapter

interface ItemTouchListener {
    fun onItemMove(from_position: Int, to_position: Int): Boolean
}