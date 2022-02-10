package com.app.storageapp.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.app.storageapp.databinding.ItemBinding
import com.app.storageapp.models.Car

class ItemAdapter(private val listener: OnItemClickListener) : ListAdapter<Car, ItemViewHolder>(ItemDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}

private class ItemDiffUtil : DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.model == newItem.model &&
                oldItem.producer == newItem.producer &&
                oldItem.year == newItem.year
    }
}