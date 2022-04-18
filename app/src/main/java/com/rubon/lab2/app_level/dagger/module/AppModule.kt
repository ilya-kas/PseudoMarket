package com.rubon.lab2.app_level.dagger.module

import android.content.Context
import androidx.navigation.NavHostController
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {
    @Provides
    fun getAppContext(): Context = context

    companion object{
        lateinit var navController: NavHostController
    }
}