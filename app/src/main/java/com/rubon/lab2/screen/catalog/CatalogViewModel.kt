package com.rubon.lab2.screen.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubon.lab2.data.database.products.ProductsDBRepository
import com.rubon.lab2.data.entity.Product
import com.rubon.lab2.data.products_data.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class CatalogViewModel @Inject constructor(private val productsRepository: ProductsRepository, private val productsDBRepository: ProductsDBRepository) {
    private lateinit var products: List<Product>
    private val _filteredProducts = MutableLiveData<List<Product>>()
    val filteredProducts: LiveData<List<Product>>
            get() = _filteredProducts

    suspend fun preload(){
        try {
            products = productsRepository.loadProducts()
            GlobalScope.launch (Dispatchers.Main){
                _filteredProducts.value = products
            }
            productsDBRepository.updateInfo(_filteredProducts.value!!)
        }catch (e: Exception){
            //means no internet
            products = productsDBRepository.loadAll()
            GlobalScope.launch (Dispatchers.Main){
                _filteredProducts.value = products
            }
        }
    }

    fun filter(mask: String){
        val newList = ArrayList<Product>()
        for (product in products)
            if (product.name.contains(mask, ignoreCase = true))
                newList += product

        _filteredProducts.value = newList
    }
}