package com.gerry.justotest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.gerry.justotest.R
import com.gerry.justotest.api.ApiResponseStatus
import com.gerry.justotest.databinding.FragmentInformationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding
    private val viewModel: InformationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildObservers()
    }

    private fun buildObservers() {
        viewModel.status.observe(requireActivity()) { status ->
            when (status) {
                is ApiResponseStatus.Error -> Toast.makeText(
                    requireContext(),
                    "Error",
                    Toast.LENGTH_SHORT
                ).show()
                is ApiResponseStatus.Loading -> Toast.makeText(
                    requireContext(),
                    "Cargando",
                    Toast.LENGTH_SHORT
                ).show()
                is ApiResponseStatus.Success -> Toast.makeText(
                    requireContext(),
                    "Exito",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {}
            }
        }

        viewModel.personList.observe(requireActivity()) { personList ->
            val person = personList[0]
            binding.name.text = person.name.last
        }
    }


}