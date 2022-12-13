package com.gerry.justotest.api

import com.gerry.justotest.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): ApiResponseStatus<T> = withContext(Dispatchers.IO){
    try {
        ApiResponseStatus.Success(call())
    } catch (e: UnknownHostException){
        ApiResponseStatus.Error(R.string.no_internet)
    } catch (e: Exception){
        ApiResponseStatus.Error(R.string.unknown_error)
    }
}