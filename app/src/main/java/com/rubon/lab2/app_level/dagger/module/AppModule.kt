package com.rubon.lab2.app_level.dagger.module

import androidx.navigation.NavHostController
import dagger.Module

@Module
class AppModule() {
    companion object{
        lateinit var navController: NavHostController
    }
}