package com.example.healthyeats.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthyeats.R
import com.example.healthyeats.data.remote.response.HistoryObject
import com.example.healthyeats.databinding.ItemHistoryBinding
import com.example.healthyeats.utils.setUrl

class HistoryAdapter : ListAdapter<HistoryObject, HistoryAdapter.ViewHolder>(DiffCallback) {
    class ViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, history: HistoryObject){
            binding.apply {
                tvNamaMakanan.text = history.foodName
                tvKalori.text = history.foodCal.toString()
                ivMakanan.setUrl(context, history.imageUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = getItem(position)
        holder.bind(holder.itemView.context, history)
    }

    companion object{
        private val DiffCallback = object : DiffUtil.ItemCallback<HistoryObject>(){
            override fun areItemsTheSame(oldItem: HistoryObject, newItem: HistoryObject): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HistoryObject, newItem: HistoryObject): Boolean {
                return oldItem == newItem
            }
        }
    }
}