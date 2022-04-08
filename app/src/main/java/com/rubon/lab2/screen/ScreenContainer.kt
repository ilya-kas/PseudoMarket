package com.rubon.lab2.screen

import androidx.compose.foundation.layout.offset
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rubon.lab2.R
import com.rubon.lab2.screen.about.AboutScreen
import com.rubon.lab2.screen.catalog.CatalogScreen
import com.rubon.lab2.screen.favorites.FavoritesScreen
import com.rubon.lab2.screen.profile.ProfileScreen
import com.rubon.lab2.ui.theme.main

@Composable
fun BottomNavigationView(navController: NavHostController){
    val items = listOf(
        NavigationItem.CATALOG,
        NavigationItem.FAVORITES,
        NavigationItem.PROFILE,
        NavigationItem.ABOUT
    )
    BottomNavigation(
        backgroundColor = main,
        contentColor = Color.Black,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.8f),
                alwaysShowLabel = true,
                selected = false,
                modifier = Modifier.offset(5.dp),
                onClick = {
                    try {
                        navController.navigate(item.title) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    } catch (e: IllegalStateException) {
                        if (e.message != "Already attached to lifecycleOwner")
                            throw e
                    }
                }
            )
        }
    }
}

@Composable
fun ScreenContainer(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.CATALOG.title) {
        composable(NavigationItem.CATALOG.title) {
            CatalogScreen()
        }
        composable(NavigationItem.FAVORITES.title) {
            FavoritesScreen()
        }
        composable(NavigationItem.PROFILE.title) {
            ProfileScreen()
        }
        composable(NavigationItem.ABOUT.title) {
            AboutScreen()
        }
    }
}

enum class NavigationItem(var title: String, var icon: Int) {
    CATALOG("Catalog", R.drawable.ic_catalog),
    FAVORITES("Favorites", R.drawable.ic_favorites),
    PROFILE("Profile", R.drawable.ic_profile),
    ABOUT("About", R.drawable.ic_info)
}