package com.rubon.lab2.app_level

import android.app.Application
import com.rubon.lab2.app_level.dagger.component.AppComponent
import com.rubon.lab2.app_level.dagger.component.DaggerAppComponent
import com.rubon.lab2.app_level.dagger.module.AppModule
import com.rubon.lab2.app_level.dagger.module.DBModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .dBModule(DBModule(applicationContext))
                .appModule(AppModule(applicationContext))
                .build()
    }

    companion object{
        lateinit var appComponent: AppComponent
    }
}