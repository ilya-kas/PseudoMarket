package com.rubon.lab2.screen.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubon.lab2.data.database.comments.CommentsRepository
import com.rubon.lab2.logic.entity.Product
import com.rubon.lab2.logic.use_case.FilledProductsHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesViewModel @Inject constructor(private val filledProductsHolder: FilledProductsHolder, private val commentsRepository: CommentsRepository){
    private val favoriteProducts = ArrayList<Product>()
    private val _favorites = MutableLiveData<List<Product>>()
    private lateinit var comments: MutableMap<Int, String>

    val favorites: LiveData<List<Product>>
        get() = _favorites

    suspend fun filterFavorites(){
        favoriteProducts.clear()
        for (product in filledProductsHolder.products)
            if (product.favorite)
                favoriteProducts += product

        GlobalScope.launch (Dispatchers.Main) {
            _favorites.value = favoriteProducts
        }

        comments = commentsRepository.loadAll()
    }

    fun editComment(product: Product, text: String){
        comments[product.hashCode()] = text

        GlobalScope.launch {
            commentsRepository.update(product, text)
        }
    }

    fun getComment(product: Product): String{
        if (comments.keys.contains(product.hashCode()))
            return comments[product.hashCode()]!!
        else
            return ""
    }
}