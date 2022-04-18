package com.rubon.lab2.screen.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubon.lab2.logic.entity.Product
import com.rubon.lab2.logic.use_case.FilledProductsHolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesViewModel @Inject constructor(private val filledProductsHolder: FilledProductsHolder){
    private val favoriteProducts = ArrayList<Product>()
    private val _favorites = MutableLiveData<List<Product>>()

    val favorites: LiveData<List<Product>>
        get() = _favorites

    fun filterFavorites(){
        favoriteProducts.clear()
        for (product in filledProductsHolder.products)
            if (product.favorite)
                favoriteProducts += product

        _favorites.value = favoriteProducts
    }
}