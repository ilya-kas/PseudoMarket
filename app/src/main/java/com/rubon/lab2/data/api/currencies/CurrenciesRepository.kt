package com.rubon.lab2.data.api.currencies

import com.rubon.lab2.data.api.currencies.source.CurrenciesSource
import com.rubon.lab2.logic.entity.Product
import com.rubon.lab2.data.api.products_data.source.ProductsSource
import com.rubon.lab2.logic.entity.CurrenciesInfo
import javax.inject.Inject

interface CurrenciesRepository {
    suspend fun loadCurrencies(): CurrenciesInfo
}

class CurrenciesRepositoryImpl @Inject constructor(private val source: CurrenciesSource): CurrenciesRepository {
    private var cachedResult: CurrenciesInfo? = null

    override suspend fun loadCurrencies(): CurrenciesInfo {
        if (cachedResult != null) return cachedResult!!

        val response = source.loadProducts()

        cachedResult = CurrenciesInfo(response[0].EUR_out.toFloat(), response[0].RUB_out.toFloat(), response[0].USD_out.toFloat())
        return cachedResult!!
    }
}