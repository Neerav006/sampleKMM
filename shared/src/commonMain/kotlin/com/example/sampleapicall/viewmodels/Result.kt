package com.example.sampleapicall.viewmodels

sealed interface Result<out T> {
    class Success<T>(val data: T) : Result<T>
    data class Error(val throwable: Throwable) : Result<Nothing>
    data object Loading : Result<Nothing>
}