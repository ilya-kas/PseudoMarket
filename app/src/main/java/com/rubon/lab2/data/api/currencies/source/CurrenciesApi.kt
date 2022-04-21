package com.rubon.lab2.data.api.currencies.source

import retrofit2.Call
import retrofit2.http.*

interface CurrenciesApi {
    @GET("/")
    fun getCurrencies(): Call<List<GetCurrencies>>
}