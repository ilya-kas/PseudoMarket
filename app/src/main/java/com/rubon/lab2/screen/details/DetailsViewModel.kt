package com.rubon.lab2.screen.details

import com.rubon.lab2.data.database.products.ProductsDBRepository
import com.rubon.lab2.logic.entity.Product
import com.rubon.lab2.logic.use_case.FilledProductsHolder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsViewModel @Inject constructor(private val productsDBRepository: ProductsDBRepository, private val filledProductsHolder: FilledProductsHolder){
    lateinit var product: Product
    var itemLink = ""

    fun markFavorite(){
        product.favorite = !product.favorite
        GlobalScope.launch {
            productsDBRepository.update(product)
        }
    }

    fun setupProduct(hash: Int){
        itemLink = ""
        for (product in filledProductsHolder.products)
            if (product.hashCode() == hash) {
                this.product = product
                break
            }
    }

    fun getLink(){
        if (itemLink != "") return
        val startUrl = "https://www.google.com/search?q=${product.name}"
        val links = Jsoup.connect(startUrl).get().select("div.yuRUbf").select("a")

        itemLink = links[0].attr("href")
    }
}