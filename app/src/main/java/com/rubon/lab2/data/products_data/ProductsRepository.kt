package com.rubon.lab2.data.products_data

import com.rubon.lab2.data.entity.Product
import com.rubon.lab2.data.products_data.source.ProductsSource
import javax.inject.Inject

interface ProductsRepository {
    suspend fun loadProducts(): List<Product>
}

class ProductsRepositoryImpl @Inject constructor(private val source: ProductsSource): ProductsRepository {
    override suspend fun loadProducts(): List<Product> {
        val result = ArrayList<Product>()

        val response = source.loadProducts()
        for (line in response)
            result += Product(line.title, line.price, line.description, line.image, line.rating.rate)


        return result
    }
}