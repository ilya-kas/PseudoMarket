package com.rubon.lab2.screen.catalog

import com.rubon.lab2.data.database.products.ProductsDBRepository
import com.rubon.lab2.data.entity.Product
import com.rubon.lab2.data.products_data.ProductsRepository
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogViewModel @Inject constructor(private val productsRepository: ProductsRepository, private val productsDBRepository: ProductsDBRepository) {
    lateinit var products: List<Product>

    suspend fun preload(){
        try {
            products = productsRepository.loadProducts()
            productsDBRepository.updateInfo(products)
        }catch (e: Exception){
            //means no internet
            products = productsDBRepository.loadAll()
        }
    }
}