package io.softeer.slideapp.adapter

interface ItemTouchListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
}