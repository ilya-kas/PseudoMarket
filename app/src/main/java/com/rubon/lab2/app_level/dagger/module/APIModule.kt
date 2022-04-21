package com.rubon.lab2.app_level.dagger.module

import com.rubon.lab2.data.api.currencies.source.CurrenciesApi
import com.rubon.lab2.data.api.products_data.source.ProductsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class APIModule{

    private fun getConvertor(): Converter.Factory = GsonConverterFactory.create()

    private fun getOkHTTPClient(): OkHttpClient = OkHttpClient().newBuilder().build()

    private fun getProductsRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com")
        .client(getOkHTTPClient())
        .addConverterFactory(getConvertor())
        .build()

    private fun getCurrenciesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://belarusbank.by")
        .client(getOkHTTPClient())
        .addConverterFactory(getConvertor())
        .build()

    @Singleton
    @Provides
    open fun getProductsApi(): ProductsApi {
        return getProductsRetrofit().create(ProductsApi::class.java)
    }

    @Singleton
    @Provides
    open fun getCurrenciesApi(): CurrenciesApi {
        return getCurrenciesRetrofit().create(CurrenciesApi::class.java)
    }
}