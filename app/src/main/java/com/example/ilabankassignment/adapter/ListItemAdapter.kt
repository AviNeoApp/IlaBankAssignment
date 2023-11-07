package com.example.ilabankassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ilabankassignment.databinding.ItemListBinding
import com.example.ilabankassignment.model.ListItemModel

class ListItemAdapter(private val listItemModel: List<ListItemModel>) :
    RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.ivIcon.setImageResource(listItemModel[position].imageId)
            binding.tvTitle.text = listItemModel[position].title
        }
    }

    override fun getItemCount(): Int {
        return listItemModel.size
    }

}