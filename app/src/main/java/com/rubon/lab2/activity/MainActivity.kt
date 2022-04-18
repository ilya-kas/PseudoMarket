package com.rubon.lab2.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.rubon.lab2.app_level.App
import com.rubon.lab2.app_level.dagger.module.AppModule
import com.rubon.lab2.logic.use_case.FilledProductsHolder
import com.rubon.lab2.screen.ScreenContainer
import com.rubon.lab2.screen.BottomNavigationView
import com.rubon.lab2.ui.theme.Lab2Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var filledProductsHolder: FilledProductsHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        GlobalScope.launch {
            filledProductsHolder.preload()

            GlobalScope.launch (Dispatchers.Main) {
                setContent {
                    Lab2Theme {
                        val navController = rememberNavController()
                        AppModule.navController = navController
                        Scaffold(
                            bottomBar = {BottomNavigationView(navController)}
                        ) { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) { // Apply the padding globally to the whole BottomNavScreensController
                                ScreenContainer(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}