package com.rubon.lab2.data.database.products

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rubon.lab2.data.entity.Product

@Entity(tableName = "products")
data class LoadableProduct (
        @PrimaryKey()
        val name: String,
        val price: Float,
        val description: String,
        val image: String,
        val rating: Float
){
        constructor(product: Product): this(product.name, product.price, product.description, product.image, product.rating)

        fun toProduct(): Product{
                return Product(name, price, description, image, rating)
        }
}