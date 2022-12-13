package com.gerry.justotest.api

import com.gerry.justotest.api.model.PersonApiresponse
import com.gerry.justotest.utils.Dictionary.BASE_URL
import com.gerry.justotest.utils.Dictionary.GET_PERSON
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()


interface ApiService{
    @GET(GET_PERSON)
    suspend fun getPerson(): PersonApiresponse
}

/**
 * No lo crea hasta que se llama por primera vez
 */
object MovieApi{
    val retrofitService: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}