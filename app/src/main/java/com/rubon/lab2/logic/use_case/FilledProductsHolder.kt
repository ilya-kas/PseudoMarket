package com.rubon.lab2.logic.use_case

import com.rubon.lab2.data.database.products.ProductsDBRepository
import com.rubon.lab2.data.api.products_data.ProductsRepository
import com.rubon.lab2.logic.entity.Product
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilledProductsHolder @Inject constructor(private val productsRepository: ProductsRepository, private val productsDBRepository: ProductsDBRepository){
    lateinit var products: List<Product>
        private set

    suspend fun preload(){
        try {
            products = productsRepository.loadProducts()
            GlobalScope.launch {
                productsDBRepository.updateAll(products)
                productsDBRepository.fillFavorites(products)
            }
        }catch (e: Exception){
            //means no internet
            products = productsDBRepository.loadAll()
        }
    }
}