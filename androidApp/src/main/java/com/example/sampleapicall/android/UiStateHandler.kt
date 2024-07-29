package com.example.sampleapicall.android

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.sampleapicall.viewmodels.Result


@Composable
fun <T> UiStateHandler(
    result: Result<T>,
    onSuccess: @Composable (data: T) -> Unit
) {
    when (result) {
        is Result.Loading -> ProgressIndicator()
        is Result.Error -> {
            Log.e("Api call error", "error.............${result.throwable.message}")
        }

        is Result.Success -> {
            print("Success api call:-------")
            onSuccess(result.data)
        }
    }
}