package com.rubon.lab2.data.api.products_data.source

import retrofit2.Call
import retrofit2.http.*


interface ProductsApi {
    @GET("/products")
    fun getProducts(): Call<List<GetProducts>>
}