package com.example.sampleapicall.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected fun <T> withProgress(
        resultHolder: MutableStateFlow<Result<T>>,
        block: suspend () -> T
    ) {
        viewModelScope.launch {
            getResult { block() }.collect {
                resultHolder.value = it
            }
        }
    }

    private fun <T> getResult(block: suspend () -> T) = flow<T> {
        emit(block())
    }.asResult()
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch { emit(Result.Error(it)) }
}