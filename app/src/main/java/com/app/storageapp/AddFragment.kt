package com.app.storageapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.app.storageapp.constants.Constants.ADD_FRAGMENT_KEY
import com.app.storageapp.constants.Constants.KEY_MODEL
import com.app.storageapp.constants.Constants.KEY_PRODUCER
import com.app.storageapp.constants.Constants.KEY_YEAR
import com.app.storageapp.databinding.DialogFragmentAddBinding

class AddFragment : DialogFragment() {

    private var _binding: DialogFragmentAddBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentAddBinding.inflate(inflater, container, false)

        with(binding) {
            buttonAdd.setOnClickListener {
                val producer = editTextProducer.text.toString()
                val year = editTextYear.text.toString()
                val model = editTextModel.text.toString()
                val bundle = bundleOf(
                    KEY_PRODUCER to producer,
                    KEY_YEAR to year,
                    KEY_MODEL to model
                )
                setFragmentResult(ADD_FRAGMENT_KEY, bundle)
                dismiss()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}