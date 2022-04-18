package com.rubon.lab2.data.database.products

import com.rubon.lab2.logic.entity.Product
import javax.inject.Inject

interface ProductsDBRepository {
    suspend fun loadAll(): List<Product>
    suspend fun updateAll(products: List<Product>)
    suspend fun update(product: Product)
    suspend fun fillFavorites(products: List<Product>)
}

class ProductsDBRepositoryImpl @Inject constructor(private val dao: ProductsDAO): ProductsDBRepository{
    override suspend fun loadAll(): List<Product> {
        val result = ArrayList<Product>()

        val stored = dao.getAll()
        for (line in stored)
            result += line.toProduct()

        return result
    }

    override suspend fun updateAll(products: List<Product>) {
        val stored = loadAll() as MutableList<Product>

        for (line in products)
            if (line in stored)
                stored -= line
            else
                dao.insert(LoadableProduct(line))

        for (old in stored)
            dao.delete(old.name.hashCode())
    }

    override suspend fun update(product: Product) {
        dao.insert(LoadableProduct(product))
    }

    override suspend fun fillFavorites(products: List<Product>) {
        val favorite = dao.getFavorites()

        val conversed = ArrayList<Product>()
        for (line in favorite)
            conversed += line.toProduct()

        for (product in products)
            if (product in conversed)
                product.favorite = true
    }
}