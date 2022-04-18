package com.rubon.lab2.screen.details

import com.rubon.lab2.data.database.products.ProductsDBRepository
import com.rubon.lab2.logic.entity.Product
import com.rubon.lab2.logic.use_case.FilledProductsHolder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsViewModel @Inject constructor(private val productsDBRepository: ProductsDBRepository, private val filledProductsHolder: FilledProductsHolder){
    lateinit var product: Product

    fun markFavorite(){
        product.favorite = !product.favorite
        GlobalScope.launch {
            productsDBRepository.update(product)
        }
    }

    fun setupProduct(name: String){
        for (product in filledProductsHolder.products)
            if (product.name == name) {
                this.product = product
                break
            }
    }
}