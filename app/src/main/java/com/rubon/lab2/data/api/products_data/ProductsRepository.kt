package com.rubon.lab2.data.api.products_data

import com.rubon.lab2.logic.entity.Product
import com.rubon.lab2.data.api.products_data.source.ProductsSource
import javax.inject.Inject

interface ProductsRepository {
    suspend fun loadProducts(): List<Product>
}

class ProductsRepositoryImpl @Inject constructor(private val source: ProductsSource): ProductsRepository {
    private var cachedResult: List<Product>? = null

    override suspend fun loadProducts(): List<Product> {
        if (cachedResult != null) return cachedResult!!

        val result = ArrayList<Product>()

        val response = source.loadProducts()
        for (line in response)
            result += Product(line.title, line.price, line.description, line.image, line.rating.rate, line.category)

        cachedResult = result
        return result
    }
}