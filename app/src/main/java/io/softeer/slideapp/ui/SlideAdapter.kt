package io.softeer.slideapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.softeer.slideapp.R
<<<<<<< HEAD
import io.softeer.slideapp.util.ItemTouchListener
import io.softeer.slideapp.databinding.HolderImageSlideBinding
=======
<<<<<<<< HEAD:app/src/main/java/io/softeer/slideapp/ui/SlideAdapter.kt
import io.softeer.slideapp.util.ItemTouchListener
import io.softeer.slideapp.databinding.HolderImageSlideBinding
import io.softeer.slideapp.databinding.HolderRectSlideBinding
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.model.ImageSlide
import io.softeer.slideapp.data.model.Slide
import io.softeer.slideapp.data.model.SquareSlide
<<<<<<< HEAD
import io.softeer.slideapp.databinding.HolderSquareSlideBinding
=======
========
import io.softeer.slideapp.data.model.ImageSlide
import io.softeer.slideapp.data.model.Slide
import io.softeer.slideapp.data.model.SquareSlide
import io.softeer.slideapp.databinding.HolderImageSlideBinding
import io.softeer.slideapp.databinding.HolderRectSlideBinding
import io.softeer.slideapp.enums.SlideType
>>>>>>>> f375d22 (Fix : 폴더 구조 변경):app/src/main/java/io/softeer/slideapp/adapter/SlideAdapter.kt
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.abs

class SlideAdapter(
<<<<<<< HEAD
    private val slideList: MutableList<Slide>,
    private val onItemClick: (Slide) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchListener {

    private val currentPosition = MutableStateFlow(0)
    private lateinit var popupMenu: PopupMenu
    private val makePopup: (Context ,View, Int) -> PopupMenu = { context, view, position ->
        PopupMenu(context, view).apply {
            this.menuInflater.inflate(R.menu.menu_slide_popup, this.menu)
            this.setOnMenuItemClickListener { item ->
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
        }
    }
=======
    private val onItemClick: (Slide) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchListener {

    private val slideList: MutableList<Slide> = mutableListOf()
    private val currentPosition = MutableStateFlow(0)
>>>>>>> f375d22 (Fix : 폴더 구조 변경)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == SlideType.Square.viewType) {
            return RectViewHolder(
<<<<<<< HEAD
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.holder_square_slide, parent, false)
=======
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.holder_rect_slide, parent, false)
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
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
            SlideType.Square.viewType -> {
                (holder as RectViewHolder).bind(slideList[position])
            }
            SlideType.Image.viewType -> {
                (holder as ImageViewHolder).bind(slideList[position])
            }
        }
    }

<<<<<<< HEAD
    fun addSlide() {
=======
    fun addSlide(slide: Slide, callback: (Slide) -> Unit) {
        slideList.add(slide)
        callback(slide)
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
        currentPosition.value = currentPosition.value + 1
        notifyItemInserted(itemCount)
    }

    fun notifyCurrentItemChanged() {
        notifyItemChanged(currentPosition.value)
    }

<<<<<<< HEAD
    inner class RectViewHolder(private val bind: HolderSquareSlideBinding): RecyclerView.ViewHolder(bind.root) {
=======
    inner class RectViewHolder(private val bind: HolderRectSlideBinding): RecyclerView.ViewHolder(bind.root) {
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
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
<<<<<<< HEAD
            setLongClickPopup(bind.root.context ,it, position)
        }
        if (bind is HolderSquareSlideBinding) {
=======
            setLongClickPopup(bind.root.context, it, position)
        }
        if (bind is HolderRectSlideBinding) {
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
            bind.slideIndex = position + 1
            bind.squareSlide = slide as SquareSlide
        }
        if (bind is HolderImageSlideBinding) {
            bind.slideIndex = position + 1
            bind.imageSlide = slide as ImageSlide
        }
    }

    private fun setLongClickPopup(context: Context, view: View, position: Int): Boolean {
<<<<<<< HEAD
        popupMenu = makePopup(context, view, position).also {
            it.show()
        }
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
        } else {
            notifyItemChanged(fromPosition)
            notifyItemChanged(toPosition)
=======
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

    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        if (currentPosition.value == from_position) {
            currentPosition.value = to_position
        }
        else if (currentPosition.value == to_position) {
            currentPosition.value = from_position
        }
        moveSlideItem(from_position, to_position, false)
        return true
    }

    private fun moveSlideItem(from_position: Int, to_position: Int, needRange: Boolean) {
        val targetSlide = slideList[from_position]
        slideList.removeAt(from_position)
        slideList.add(to_position, targetSlide)
        notifyItemMoved(from_position, to_position)
        if (needRange) {
            val startPos = if (from_position > to_position) to_position else from_position
            notifyItemRangeChanged(startPos, abs(from_position - to_position) + 1)
        }
        if (!needRange) {
            notifyItemChanged(from_position)
            notifyItemChanged(to_position)
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
        }
    }
}