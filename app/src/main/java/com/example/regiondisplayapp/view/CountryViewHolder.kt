package com.example.regiondisplayapp.view

import androidx.recyclerview.widget.RecyclerView
import com.example.regiondisplayapp.R
import com.example.regiondisplayapp.databinding.ItemBinding
import com.example.regiondisplayapp.model.Country

class CountryViewHolder(
    private val binding: ItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(country: Country) {
        binding.name.text =
            itemView.context.getString(R.string.country_name_string, country.name, country.region)
        binding.code.text = country.code
        binding.capital.text = country.capital
    }
}
