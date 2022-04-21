package com.rubon.lab2.screen.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubon.lab2.logic.entity.FilterState
import com.rubon.lab2.logic.entity.FilterType
import com.rubon.lab2.logic.entity.Product
import com.rubon.lab2.logic.use_case.FilledProductsHolder
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

@Singleton
class CatalogViewModel @Inject constructor(private val filledProductsHolder: FilledProductsHolder) {
    private val filter = FilterState()
    private lateinit var products: List<Product>
    private val _filteredProducts = MutableLiveData<List<Product>>()
    val filteredProducts: LiveData<List<Product>>
            get() = _filteredProducts

    fun getCategories(): Set<String>{
        val result = HashSet<String>()
        for (product in products)
            result += product.category

        return result
    }

    fun preload(){
        products = filledProductsHolder.products
        _filteredProducts.value = products
    }

    fun addFilter(type: FilterType, value: Any){
        when (type){
            FilterType.MASK -> filter.mask = value as String
            FilterType.CATEGORY -> filter.categories += value as String
            FilterType.PRICE_MIN -> filter.minPrice = value as Double
            FilterType.PRICE_MAX -> filter.maxPrice = value as Double
        }
        filter()
    }

    fun removeFilter(type: FilterType, value: Any){
        when (type){
            FilterType.CATEGORY -> filter.categories -= value as String
            FilterType.PRICE_MIN -> filter.minPrice = 0.0
            FilterType.PRICE_MAX -> filter.maxPrice = 0.0
        }
        filter()
    }

    fun filter(){
        val newList = ArrayList<Product>()
        for (product in products)
            if (product.name.contains(filter.mask, ignoreCase = true) &&
                (filter.categories.contains(product.category) || filter.categories.size == 0) &&
                (filter.minPrice <= product.price) &&
                (filter.maxPrice >= product.price || filter.maxPrice == 0.0))
                newList += product

        _filteredProducts.value = newList
    }

    fun findProduct(product: Product): Int{
        for ((i,item) in filteredProducts.value!!.withIndex())
            if (item == product)
                return i
        return -1
    }
}