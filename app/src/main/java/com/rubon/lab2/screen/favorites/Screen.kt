package com.rubon.lab2.screen.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rubon.lab2.app_level.App
import com.rubon.lab2.app_level.dagger.module.AppModule
import com.rubon.lab2.logic.entity.Product
import com.rubon.lab2.screen.NavigationItem

private val viewModel = App.appComponent.getFavoritesViewModel()

@Preview(showBackground = true)
@Composable
fun FavoritesScreen(){
    viewModel.filterFavorites()

    val filteredItems = viewModel.favorites.observeAsState()

    if (filteredItems.value!!.isEmpty())
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No favorite products :(")
        }
    else
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = filteredItems.value!!,
                itemContent = {
                    ItemLine(it)
                })
        }
}

@Composable
private fun ItemLine(item: Product){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp, vertical = 5.dp)
            .clickable { navigateToDetails(item) },
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Row (
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(50.dp)
        ){
            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = "image of product",
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(50.dp)
            )

            var text = item.name
            while (text.length > 20) {
                val words = text.split(" ")
                text = words.subList(0, words.size-1).joinToString(separator = " ")
            }
            Column {
                Text(text = text)
                Text(text = "rating: ${item.rating}")
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd){
                Text(
                    text = "${item.price} $")
            }
        }
    }
}

internal fun navigateToDetails(item: Product) {
    AppModule.navController.navigate(NavigationItem.WebItem.title+"/${item.name}")
}