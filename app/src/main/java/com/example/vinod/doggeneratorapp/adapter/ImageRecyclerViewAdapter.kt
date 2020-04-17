package com.example.vinod.doggeneratorapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinod.doggeneratorapp.R
import com.example.vinod.doggeneratorapp.base.utils.setImageLoader
import com.example.vinod.doggeneratorapp.databinding.ImageviewItemLayoutBinding

class ImageRecyclerViewAdapter(private val imageUrlList: ArrayList<String>) : RecyclerView.Adapter<ImageRecyclerViewAdapter.ImageViewHolder>() {

  override fun onCreateViewHolder(
    parent: ViewGroup, viewType: Int
  ): ImageViewHolder {
    return ImageViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), R.layout.imageview_item_layout, parent, false
      )
    )
  }

  inner class ImageViewHolder(private val binder: ImageviewItemLayoutBinding) :
    RecyclerView.ViewHolder(binder.root) {
    fun bind(position: Int) {
      setImageLoader(binder.ivImageView, imageUrlList[position])
      val finalPosition = position + 1
      binder.tvCount.text = "Count : $finalPosition"
    }
  }

  override fun getItemCount(): Int {
    return imageUrlList.size
  }

  fun clearImageUrlList() {
    imageUrlList.clear()
    notifyDataSetChanged()
  }

  override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
    holder.bind(position)
  }

}