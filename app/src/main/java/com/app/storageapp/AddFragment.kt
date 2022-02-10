package com.app.storageapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.app.storageapp.databinding.DialogFragmentAddBinding

class AddFragment : DialogFragment() {

    private lateinit var binding: DialogFragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentAddBinding.inflate(inflater, container, false)

        binding.buttonAdd.setOnClickListener {
            //setFragmentResult()
        }

        return binding.root
    }
}