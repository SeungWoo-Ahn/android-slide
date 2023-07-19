package io.softeer.slideapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.softeer.slideapp.R
import io.softeer.slideapp.databinding.HolderImageSlideBinding
import io.softeer.slideapp.databinding.HolderRectSlideBinding
import io.softeer.slideapp.enum.SlideType
import io.softeer.slideapp.model.Slide
import kotlinx.coroutines.flow.MutableStateFlow

class SlideAdapter(
    private val onItemClick: (Slide) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchListener {

    private val slideList: MutableList<Slide> = mutableListOf()
    private val currentPosition = MutableStateFlow(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == SlideType.Rect.viewType){
            return RectViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.holder_rect_slide, parent, false)
            )
        }
        return ImageViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.holder_image_slide, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return slideList[position].type.viewType
    }

    override fun getItemCount(): Int {
        return slideList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(slideList[position].type.viewType) {
            SlideType.Rect.viewType -> {
                (holder as RectViewHolder).bind(slideList[position])
            }
            SlideType.Image.viewType -> {
                (holder as ImageViewHolder).bind(slideList[position])
            }
        }
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

    inner class RectViewHolder(private val bind: HolderRectSlideBinding): RecyclerView.ViewHolder(bind.root) {
        fun bind(slide: Slide) {
            bind.slideIndex = adapterPosition + 1
            bind.slide = slide
            bind.root.setOnClickListener {
                onItemClick(slide)
                currentPosition.value = adapterPosition
            }
        }
    }

    inner class ImageViewHolder(private val bind: HolderImageSlideBinding): RecyclerView.ViewHolder(bind.root) {
        fun bind(slide: Slide) {
            bind.slideIndex = adapterPosition + 1
            bind.root.setOnClickListener {
                onItemClick(slide)
                currentPosition.value = adapterPosition
            }
        }
    }

    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        val targetSlide = slideList[from_position]
        slideList.removeAt(from_position)
        slideList.add(to_position, targetSlide)
        notifyItemMoved(from_position, to_position)
        notifyItemChanged(from_position)
        notifyItemChanged(to_position)
        currentPosition.value = to_position
        return true
    }
}