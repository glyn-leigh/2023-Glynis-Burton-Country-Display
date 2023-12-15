package com.example.regiondisplayapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.regiondisplayapp.databinding.ItemBinding
import com.example.regiondisplayapp.model.Country

class CountryAdapter(
    private var countryList: List<Country>
) : RecyclerView.Adapter<CountryViewHolder>() {

    private lateinit var binding: ItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    fun setData(list: List<Country>) {
        countryList = list
    }


}
