package com.rubon.lab2.app_level.dagger.component

import android.content.Context
import com.rubon.lab2.activity.MainActivity
import com.rubon.lab2.app_level.dagger.module.APIModule
import com.rubon.lab2.app_level.dagger.module.AppModule
import com.rubon.lab2.app_level.dagger.module.BindModule
import com.rubon.lab2.app_level.dagger.module.DBModule
import com.rubon.lab2.logic.use_case.FilledProductsHolder
import com.rubon.lab2.screen.catalog.CatalogViewModel
import com.rubon.lab2.screen.details.DetailsViewModel
import com.rubon.lab2.screen.favorites.FavoritesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BindModule::class, AppModule::class, DBModule::class, APIModule::class])
interface AppComponent {
    fun getContext(): Context

    fun getCatalogViewModel(): CatalogViewModel
    fun getDetailsViewModel(): DetailsViewModel
    fun getFavoritesViewModel(): FavoritesViewModel

    fun inject(activity: MainActivity)
}