package com.app.storageapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.app.storageapp.constants.Constants.DATA_STRING_DELIMITER
import com.app.storageapp.databinding.DialogFragmentDetailBinding

private const val KEY_PRODUCER = "PRODUCER"
private const val KEY_YEAR = "YEAR"
private const val KEY_MODEL = "MODEL"

class DetailFragment : DialogFragment() {

    private lateinit var binding: DialogFragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentDetailBinding.inflate(inflater, container, false)

        arguments?.let {
            val producer = it.getString(KEY_PRODUCER) ?: ""
            val year = it.getString(KEY_YEAR) ?: ""
            val model = it.getString(KEY_MODEL) ?: ""
            initViews(producer, year, model)
        }

        return binding.root
    }

    private fun initViews(producer: String, year: String, model: String) {
        with(binding) {
            editTextProducer.setText(producer)
            editTextYear.setText(year)
            editTextModel.setText(model)
        }
    }

    companion object {
        fun newInstance(data: String): DetailFragment {
            val carData = data.split(DATA_STRING_DELIMITER)
            val args = bundleOf(
                KEY_PRODUCER to carData[0],
                KEY_YEAR to carData[1],
                KEY_MODEL to carData[2],
            )
            val fragment = DetailFragment().apply {
                arguments = args
            }
            return fragment
        }
    }
}