package com.rubon.lab2.ui.theme

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppStateViewModel @Inject constructor(){
    private var firstTime = true
    val isLightTheme = MutableLiveData(true)

    fun setupTheme(deviceTheme: Boolean){
        if (!firstTime) return
        isLightTheme.value = true
        firstTime = false
    }
}