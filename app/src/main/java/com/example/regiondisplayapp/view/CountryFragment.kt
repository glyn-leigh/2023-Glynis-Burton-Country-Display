package com.example.regiondisplayapp.view

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.regiondisplayapp.R
import com.example.regiondisplayapp.common.ResponseState
import com.example.regiondisplayapp.databinding.FragmentCountryListBinding
import com.example.regiondisplayapp.model.Country
import com.example.regiondisplayapp.viewmodel.CountryViewModel

class CountryFragment : Fragment() {
    private var _binding: FragmentCountryListBinding? = null
    private val binding get() = _binding!!
    private var recyclerViewState: Parcelable? = null
    private var adapter: CountryAdapter = CountryAdapter(listOf())
    private val viewModel: CountryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryListBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.countryList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResponseState.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.warningBanner.visibility = View.INVISIBLE
                    adapter.setData(state.data)
                    adapter.notifyDataSetChanged()
                    binding.recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
                }

                is ResponseState.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.warningBanner.visibility = View.VISIBLE
                    if (state.message == "INTERNET") {
                        binding.warningText.text = getString(R.string.no_internet_warning)
                    } else {
                        binding.warningText.text = state.message
                    }

                }

            }
        }


        binding.recyclerView.apply {
            adapter = this@CountryFragment.adapter
            setHasFixedSize(true)
        }
        savedInstanceState?.let {
            recyclerViewState = it.getParcelable("recycler_state")
        }
    }
}