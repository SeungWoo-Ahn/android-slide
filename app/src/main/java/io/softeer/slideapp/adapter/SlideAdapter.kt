package io.softeer.slideapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.softeer.slideapp.R
import io.softeer.slideapp.databinding.HolderImageSlideBinding
import io.softeer.slideapp.databinding.HolderRectSlideBinding
import io.softeer.slideapp.enums.SlideType
import io.softeer.slideapp.model.ImageSlide
import io.softeer.slideapp.model.Slide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.abs

class SlideAdapter(
    private val onItemClick: (Slide) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchListener {

    private val slideList: MutableList<Slide> = mutableListOf()
    private val currentPosition = MutableStateFlow(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == SlideType.Rect.viewType) {
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
            setHolderBind(bind, slide, adapterPosition)
        }
    }

    inner class ImageViewHolder(private val bind: HolderImageSlideBinding): RecyclerView.ViewHolder(bind.root) {
        fun bind(slide: Slide) {
            setHolderBind(bind, slide, adapterPosition)
        }
    }

    private fun setHolderBind(bind: ViewDataBinding, slide: Slide, position: Int) {
        bind.root.setOnClickListener {
            onItemClick(slide)
            currentPosition.value = position
        }
        bind.root.setOnLongClickListener {
            setLongClickPopup(bind.root.context, it, position)
        }
        if (bind is HolderRectSlideBinding) {
            bind.slideIndex = position + 1
            bind.slide = slide
        }
        if (bind is HolderImageSlideBinding) {
            bind.slideIndex = position + 1
            bind.imageSlide = slide as ImageSlide
        }
    }

    private fun setLongClickPopup(context: Context, view: View, position: Int): Boolean {
        val popup = PopupMenu(context, view)
        popup.menuInflater.inflate(R.menu.menu_slide_popup, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.menu_send_to_back -> {
                    if (position != itemCount - 1) {
                        moveSlideItem(position, itemCount - 1, true)
                    }
                }
                R.id.menu_send_back -> {
                    if (position != itemCount - 1) {
                        moveSlideItem(position, position + 1, false)
                    }
                }
                R.id.menu_send_front -> {
                    if (position != 0) {
                        moveSlideItem(position, position - 1, false)
                    }
                }
                R.id.menu_send_to_front -> {
                    if (position != 0) {
                        moveSlideItem(position, 0, true)
                    }
                }
            }
            true
        }
        popup.show()
        return true
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (currentPosition.value == fromPosition) {
            currentPosition.value = toPosition
        }
        else if (currentPosition.value == toPosition) {
            currentPosition.value = fromPosition
        }
        moveSlideItem(fromPosition, toPosition, false)
        return true
    }

    private fun moveSlideItem(fromPosition: Int, toPosition: Int, needRange: Boolean) {
        val targetSlide = slideList[fromPosition]
        slideList.removeAt(fromPosition)
        slideList.add(toPosition, targetSlide)
        notifyItemMoved(fromPosition, toPosition)
        if (needRange) {
            val startPos = if (fromPosition > toPosition) toPosition else fromPosition
            notifyItemRangeChanged(startPos, abs(fromPosition - toPosition) + 1)
        }
        if (!needRange) {
            notifyItemChanged(fromPosition)
            notifyItemChanged(toPosition)
        }
    }
}