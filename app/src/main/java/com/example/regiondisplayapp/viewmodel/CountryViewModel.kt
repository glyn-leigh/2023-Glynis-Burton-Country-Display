package com.example.regiondisplayapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.regiondisplayapp.common.ResponseState
import com.example.regiondisplayapp.model.Country
import com.example.regiondisplayapp.network.ConnectivityHelper
import com.example.regiondisplayapp.network.RetrofitHelper

class CountryViewModel(app: Application) : AndroidViewModel(app) {

    private val _countryList: LiveData<ResponseState<List<Country>>> = liveData {
        emit(ResponseState.Loading)

        if (ConnectivityHelper.networkConnection(app)) {
            val response = RetrofitHelper.apiService.getCountries()
            if (response.isSuccessful) {
                emit(ResponseState.Success(response.body()!!))
            } else {
                emit(ResponseState.Error(response.message().takeIf { !it.isNullOrEmpty() }
                    ?: "An unexpected error occurred"))
            }
        } else {
            emit(ResponseState.Error("INTERNET"))
        }
    }
    val countryList = _countryList

}

