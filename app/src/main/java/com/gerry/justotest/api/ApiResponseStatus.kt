package com.gerry.justotest.api

sealed class ApiResponseStatus<T> {
    class Success<T>(val data: T): ApiResponseStatus<T>()
    class Loading<T>: ApiResponseStatus<T>()
    class Error<T>(val messageID: Int): ApiResponseStatus<T>()
}