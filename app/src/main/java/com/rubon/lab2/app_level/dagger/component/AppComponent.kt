package com.rubon.lab2.app_level.dagger.component

import android.content.Context
import com.rubon.lab2.app_level.dagger.module.APIModule
import com.rubon.lab2.app_level.dagger.module.AppModule
import com.rubon.lab2.app_level.dagger.module.BindModule
import com.rubon.lab2.app_level.dagger.module.DBModule
import com.rubon.lab2.screen.catalog.CatalogViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BindModule::class, AppModule::class, DBModule::class, APIModule::class])
interface AppComponent {
    fun getContext(): Context

    fun getCatalogViewModel(): CatalogViewModel
}