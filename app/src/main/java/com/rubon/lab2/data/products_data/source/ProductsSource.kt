package com.rubon.lab2.data.products_data.source

import retrofit2.awaitResponse
import java.lang.NullPointerException
import javax.inject.Inject

interface ProductsSource {
    suspend fun loadProducts(): List<GetProducts>
}

class ProductsSourceImpl @Inject constructor(private val api: ProductsApi): ProductsSource {
    override suspend fun loadProducts(): List<GetProducts> {
        val result = api.getProducts().awaitResponse()
        if (result.isSuccessful)
            return result.body()!!
        else
            throw NullPointerException()
    }
}