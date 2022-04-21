package com.rubon.lab2.app_level.dagger.module

import com.rubon.lab2.data.api.currencies.CurrenciesRepository
import com.rubon.lab2.data.api.currencies.CurrenciesRepositoryImpl
import com.rubon.lab2.data.api.currencies.source.CurrenciesSource
import com.rubon.lab2.data.api.currencies.source.CurrenciesSourceImpl
import com.rubon.lab2.data.database.comments.CommentsRepository
import com.rubon.lab2.data.database.comments.CommentsRepositoryImpl
import com.rubon.lab2.data.database.products.ProductsDBRepository
import com.rubon.lab2.data.database.products.ProductsDBRepositoryImpl
import com.rubon.lab2.data.api.products_data.ProductsRepository
import com.rubon.lab2.data.api.products_data.ProductsRepositoryImpl
import com.rubon.lab2.data.api.products_data.source.ProductsSource
import com.rubon.lab2.data.api.products_data.source.ProductsSourceImpl
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
    @Binds
    fun bindCommentsRepository(provider: CommentsRepositoryImpl): CommentsRepository
    @Binds
    fun bindCurrenciesRepository(provider: CurrenciesRepositoryImpl): CurrenciesRepository
    @Binds
    fun bindCurrenciesSource(provider: CurrenciesSourceImpl): CurrenciesSource
}