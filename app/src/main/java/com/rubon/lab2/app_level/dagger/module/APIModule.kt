package com.rubon.lab2.app_level.dagger.module

import com.rubon.lab2.data.products_data.source.ProductsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://fakestoreapi.com"

@Module
open class APIModule{

    @Provides
    fun getConvertor(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun getOkHTTPClient(): OkHttpClient = OkHttpClient().newBuilder().build()

    @Provides
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getOkHTTPClient())
        .addConverterFactory(getConvertor())
        .build()

    @Singleton
    @Provides
    open fun getProductsApi(): ProductsApi {
        return getRetrofit().create(ProductsApi::class.java)
    }
}