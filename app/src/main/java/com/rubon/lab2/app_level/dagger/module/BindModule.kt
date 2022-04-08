package com.rubon.lab2.app_level.dagger.module

import com.rubon.lab2.data.database.products.ProductsDBRepository
import com.rubon.lab2.data.database.products.ProductsDBRepositoryImpl
import com.rubon.lab2.data.products_data.ProductsRepository
import com.rubon.lab2.data.products_data.ProductsRepositoryImpl
import com.rubon.lab2.data.products_data.source.ProductsSource
import com.rubon.lab2.data.products_data.source.ProductsSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface BindModule {
    @Binds
    fun bindProductsRepository(provider: ProductsRepositoryImpl): ProductsRepository
    @Binds
    fun bindProductsSource(provider: ProductsSourceImpl): ProductsSource
    @Binds
    fun bindProductsDBRepository(provider: ProductsDBRepositoryImpl): ProductsDBRepository
}