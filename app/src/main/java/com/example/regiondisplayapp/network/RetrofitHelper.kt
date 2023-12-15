package com.example.regiondisplayapp.network

import com.example.regiondisplayapp.common.URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val rf: Retrofit =  Retrofit.Builder()
        .baseUrl(URL)
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY)).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService:CountryService = rf.create(CountryService::class.java)


}