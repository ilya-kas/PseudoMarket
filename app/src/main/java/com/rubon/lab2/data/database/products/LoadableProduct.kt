package com.rubon.lab2.data.database.products

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rubon.lab2.logic.entity.Product

@Entity(tableName = "products")
data class LoadableProduct (
    @PrimaryKey()
    val hash: Int,

    val name: String,
    val price: Float,
    val description: String,
    val image: String,
    val rating: Float,
    val category: String,

    val favorite: Boolean
){
    constructor(product: Product): this(product.name.hashCode(), product.name, product.price, product.description, product.image, product.rating, product.category, product.favorite)

    fun toProduct(): Product{
        return Product(name, price, description, image, rating, category, favorite)
    }
}