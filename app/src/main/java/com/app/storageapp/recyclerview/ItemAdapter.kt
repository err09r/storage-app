package com.app.storageapp.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.app.storageapp.databinding.ItemBinding
import com.app.storageapp.models.Car
import java.util.UUID

private const val TAG = "ItemAdapter"

class ItemAdapter(private val listener: OnItemClickListener) : ListAdapter<Car, ItemViewHolder>(ItemDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        Log.d(TAG, "onCreateViewHolder: ${currentList}")
        return ItemViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    interface OnItemClickListener {
        fun onItemClick(id: Int)
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

    override fun getChangePayload(oldItem: Car, newItem: Car) = Any()
}