package com.rubon.lab2.data.products_data.source

data class GetProducts(
    val title: String,
    val price: Float,
    val description: String,
    val image: String,
    val rating: GetRating
)

data class GetRating(
    val rate: Float,
    val count: Int
)