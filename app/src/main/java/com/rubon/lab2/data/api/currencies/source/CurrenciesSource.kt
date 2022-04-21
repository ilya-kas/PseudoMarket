package com.rubon.lab2.data.api.currencies.source

import retrofit2.awaitResponse
import java.lang.NullPointerException
import javax.inject.Inject

interface CurrenciesSource {
    suspend fun loadProducts(): List<GetCurrencies>
}

class CurrenciesSourceImpl @Inject constructor(private val api: CurrenciesApi): CurrenciesSource {
    override suspend fun loadProducts(): List<GetCurrencies> {
        val result = api.getCurrencies().awaitResponse()
        if (result.isSuccessful)
            return result.body()!!
        else
            throw NullPointerException()
    }
}