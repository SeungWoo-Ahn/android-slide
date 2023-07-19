package io.softeer.slideapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.softeer.slideapp.R
import io.softeer.slideapp.databinding.HolderSlideBinding
import io.softeer.slideapp.model.Slide
import kotlinx.coroutines.flow.MutableStateFlow

class SlideAdapter(
    private val onItemClick: (Slide) -> Unit
) : RecyclerView.Adapter<SlideAdapter.ViewHolder>(), ItemTouchListener {

    private val slideList: MutableList<Slide> = mutableListOf()
    private val currentPosition = MutableStateFlow(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.holder_slide, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return slideList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(slideList[position])
    }

    fun addSlide(slide: Slide, callback: (Slide) -> Unit) {
        slideList.add(slide)
        callback(slide)
        currentPosition.value = currentPosition.value + 1
        notifyItemInserted(itemCount)
    }

    fun notifyCurrentItemChanged() {
        notifyItemChanged(currentPosition.value)
    }

    inner class ViewHolder(private val bind: HolderSlideBinding): RecyclerView.ViewHolder(bind.root) {
        fun bind(slide: Slide) {
            bind.slideIndex = adapterPosition + 1
            bind.slide = slide
            bind.root.setOnClickListener {
                onItemClick(slide)
                currentPosition.value = adapterPosition
            }
        }
    }

    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        val targetSlide = slideList[from_position]
        if (currentPosition.value == from_position) {
            currentPosition.value = to_position
        }
        if (currentPosition.value == to_position) {
            currentPosition.value = from_position
        }
        slideList.removeAt(from_position)
        slideList.add(to_position, targetSlide)
        notifyItemMoved(from_position, to_position)
        notifyItemChanged(from_position)
        notifyItemChanged(to_position)
        return true
    }

}