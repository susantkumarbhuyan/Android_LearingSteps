package com.zerocoder.repository

import com.zerocoder.model.QuoteList

sealed class BaseResponse<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : BaseResponse<T>()
    class Success<T>(data: T? = null) : BaseResponse<T>(data = data)
    class Error<T>(errorMessage: String?) : BaseResponse<T>(errorMessage = errorMessage)
}
