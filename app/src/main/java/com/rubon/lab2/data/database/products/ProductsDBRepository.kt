package com.rubon.lab2.data.database.products

import com.rubon.lab2.data.entity.Product
import javax.inject.Inject

interface ProductsDBRepository {
    suspend fun loadAll(): List<Product>
    suspend fun updateInfo(products: List<Product>)
}

class ProductsDBRepositoryImpl @Inject constructor(private val dao: ProductsDAO): ProductsDBRepository{
    override suspend fun loadAll(): List<Product> {
        val result = ArrayList<Product>()

        val stored = dao.getAll()
        for (line in stored)
            result += line.toProduct()

        return result
    }

    override suspend fun updateInfo(products: List<Product>) {
        dao.delete()
        for (line in products)
            dao.insert(LoadableProduct(line))
    }

}