package com.gerry.justotest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
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
        configSwipe()
    }

    private fun configSwipe() {
        binding.swipe.setOnRefreshListener {
            viewModel.downloadAgain()
        }
    }

    private fun buildObservers() {
        viewModel.status.observe(requireActivity()) { status ->
            when (status) {
                is ApiResponseStatus.Error -> {
                    binding.swipe.isRefreshing = false
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_refresh),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is ApiResponseStatus.Loading -> {
                    binding.swipe.isRefreshing = true
                }
                is ApiResponseStatus.Success -> {
                    binding.swipe.isRefreshing = false
                }
                else -> {}
            }
        }

        viewModel.personList.observe(requireActivity()) { personList ->
            for (person in personList){
                binding.tvName.text = person.getFullName(person.name.first, person.name.last)
                binding.tvLocation.text = person.location.city
                binding.tvEmail.text = person.email
                binding.tvPhone.text = person.phone
                Glide
                    .with(binding.imageView.context)
                    .load(person.picture.large)
                    .centerCrop()
                    .into(binding.imageView)
            }
        }
    }

}