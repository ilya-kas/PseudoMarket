package com.rubon.lab2.logic.entity

class Product(
    val name: String,
    val price: Float,
    val description: String,
    val image: String,
    val rating: Float,
    val category: String,

    var favorite: Boolean = false
){
    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (name != other.name) return false
        if (price != other.price) return false
        if (description != other.description) return false
        if (image != other.image) return false
        if (rating != other.rating) return false
        if (category != other.category) return false

        return true
    }
}