package com.app.storageapp.recyclerview

import android.opengl.GLES30
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.storageapp.databinding.ItemBinding
import com.app.storageapp.models.Car

class ItemViewHolder(private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(car: Car) {
        binding.textView4.text = car.producer
        binding.textView5.text = car.year.toString()
        binding.textView6.text = car.model
    }
}