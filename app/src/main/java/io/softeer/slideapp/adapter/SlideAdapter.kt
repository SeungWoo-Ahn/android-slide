package io.softeer.slideapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.softeer.slideapp.R
import io.softeer.slideapp.databinding.HolderSlideBinding
import io.softeer.slideapp.model.Slide

class SlideAdapter : RecyclerView.Adapter<SlideAdapter.ViewHolder>() {

    private val slideList: MutableList<Slide> = mutableListOf()

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

    fun addSlide(slide: Slide) {
        slideList.add(slide)
        notifyItemInserted(itemCount)
    }

    inner class ViewHolder(private val bind: HolderSlideBinding): RecyclerView.ViewHolder(bind.root) {
        fun bind(slide: Slide) {
            bind.slideIndex = adapterPosition + 1
        }
    }

}