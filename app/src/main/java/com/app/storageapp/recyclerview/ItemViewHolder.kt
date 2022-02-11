package com.app.storageapp.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.app.storageapp.databinding.ItemBinding
import com.app.storageapp.models.Car

class ItemViewHolder(
    private val binding: ItemBinding,
    private val listener: ItemAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(car: Car) {
        binding.textView4.text = car.producer
        binding.textView5.text = car.year.toString()
        binding.textView6.text = car.model
        binding.root.setOnClickListener {
            listener.onItemClick(car.id)
        }
    }
}