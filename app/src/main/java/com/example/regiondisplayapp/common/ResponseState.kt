package com.example.regiondisplayapp.common

sealed class ResponseState<out T>{
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error(val message: String) : ResponseState<Nothing>()
    data object Loading : ResponseState<Nothing>()
}