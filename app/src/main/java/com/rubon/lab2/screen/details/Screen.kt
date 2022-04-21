package com.rubon.lab2.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rubon.lab2.R
import com.rubon.lab2.app_level.App
import com.rubon.lab2.app_level.dagger.module.AppModule
import com.rubon.lab2.logic.entity.Product
import com.rubon.lab2.screen.NavigationItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private val viewModel = App.appComponent.getDetailsViewModel()

@Composable
fun DetailsScreen(productHash: Int){
    viewModel.setupProduct(productHash)
    val product = viewModel.product

    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 3.dp, vertical = 5.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(vertical = 10.dp)) {
            FavoritesButtonWithContainer()

            //Product photo
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = rememberAsyncImagePainter(product.image),
                    contentDescription = "Product photo",
                    modifier = Modifier
                        .padding(40.dp)
                        .size(200.dp)
                )
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(10.dp))

            Description(product)

            Text(
                modifier = Modifier.clickable { navigateToWebView() }.padding(horizontal = 10.dp),
                text = "Watch original",
                color = Color.Cyan)
        }
    }
}

@Composable
private fun FavoritesButtonWithContainer(){
    //favorites button
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
        val favoriteImg = remember{ mutableStateOf(R.drawable.favorites)}
        if (viewModel.product.favorite)
            favoriteImg.value = R.drawable.favorites_filled
        else
            favoriteImg.value = R.drawable.favorites
        Image(
            painter = painterResource(id = favoriteImg.value),
            contentDescription = "favorites button",
            Modifier
                .padding(3.dp)
                .size(40.dp)
                .clickable {
                    viewModel.markFavorite()
                    if (viewModel.product.favorite)
                        favoriteImg.value = R.drawable.favorites_filled
                    else
                        favoriteImg.value = R.drawable.favorites
                }
        )
    }
}

@Composable
private fun Description(product: Product){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Text(text = "Name: "+ product.name)
        Text(text = "Category: "+ product.category)
        Text(text = "Rating: "+ product.rating.toString())
        Text(text = product.description)
        Text(text = "Price: "+ product.price.toString() + "$")
    }
}

internal fun navigateToWebView() {
    GlobalScope.launch {
        viewModel.getLink()

        GlobalScope.launch(Dispatchers.Main) {
            AppModule.navController.navigate(NavigationItem.WebItem.title)
        }
    }
}