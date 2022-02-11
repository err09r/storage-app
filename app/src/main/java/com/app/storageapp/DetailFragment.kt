package com.app.storageapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.app.storageapp.constants.Constants.CAR_KEY
import com.app.storageapp.constants.Constants.DELETE_CAR
import com.app.storageapp.constants.Constants.DETAIL_FRAGMENT_KEY
import com.app.storageapp.constants.Constants.UPDATE_CAR
import com.app.storageapp.databinding.DialogFragmentDetailBinding
import com.app.storageapp.models.Car

class DetailFragment : DialogFragment() {

    private var _binding: DialogFragmentDetailBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentDetailBinding.inflate(inflater, container, false)

        arguments?.let {
            val car = it.getSerializable(CAR_KEY) as Car
            initViews(car)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews(car: Car) {
        with(binding) {
            editTextProducer.setText(car.producer)
            editTextYear.setText(car.year.toString())
            editTextModel.setText(car.model)
            buttonUpdate.setOnClickListener {
                val producer = editTextProducer.text.toString()
                val year = editTextYear.text.toString().toIntOrNull() ?: 0
                val model = editTextModel.text.toString()
                val bundle = Bundle().apply {
                    putSerializable(UPDATE_CAR, Car(car.id, producer, year, model))
                }
                setFragmentResult(DETAIL_FRAGMENT_KEY, bundle)
                dismiss()
            }
            buttonDelete.setOnClickListener {
                val bundle = Bundle().apply {
                    putSerializable(DELETE_CAR, car)
                }
                setFragmentResult(DETAIL_FRAGMENT_KEY, bundle)
                dismiss()
            }
        }
    }

    companion object {
        fun newInstance(car: Car): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle().apply {
                putSerializable(CAR_KEY, car)
            }
            fragment.arguments = args
            return fragment
        }
    }
}