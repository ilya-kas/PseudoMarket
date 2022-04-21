package com.rubon.lab2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.rubon.lab2.app_level.App

private val DarkColorPalette = darkColors(
    primary = main_dark,
    background = background_dark
)

private val LightColorPalette = lightColors(
    primary = main,
    background = background
)

private val viewModel = App.appComponent.getAppStateViewModel()

@Composable
fun Lab2Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    viewModel.setupTheme(!darkTheme)

    val isLightMode = viewModel.isLightTheme.observeAsState()

    val colors = if (isLightMode.value!!) {
        LightColorPalette
    } else {
        DarkColorPalette
    }
    val typography = if (isLightMode.value!!){
        LightTypography
    } else {
        DarkTypography
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}