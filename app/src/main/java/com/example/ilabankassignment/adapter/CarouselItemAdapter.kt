package com.example.ilabankassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ilabankassignment.databinding.ItemCarouselBinding
import com.example.ilabankassignment.model.CarouselAndListItemModel

class CarouselItemAdapter(private val carouselAndListModelListItem: List<CarouselAndListItemModel>):
    RecyclerView.Adapter<CarouselItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCarouselBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.ivCarousel.setImageResource(
                carouselAndListModelListItem[position].carouselImageId)
        }
    }

    override fun getItemCount(): Int {
        return carouselAndListModelListItem.size
    }
}