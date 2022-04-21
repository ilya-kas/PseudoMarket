package com.rubon.lab2.screen.favorites

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rubon.lab2.app_level.App
import com.rubon.lab2.app_level.dagger.module.AppModule
import com.rubon.lab2.logic.entity.FilterType
import com.rubon.lab2.logic.entity.Product
import com.rubon.lab2.screen.NavigationItem
import com.rubon.lab2.ui.theme.main

private val viewModel = App.appComponent.getFavoritesViewModel()

@Preview(showBackground = true)
@Composable
fun FavoritesScreen(){
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
                    ItemCard(it)
                })
        }
}

@Composable
private fun ItemCard(item: Product){
    val expanded = remember { mutableStateOf(false) }

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp, vertical = 5.dp)
            .clickable { navigateToDetails(item) },
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {
            ItemInfoLine(item = item) { expanded.value = !expanded.value }

            if (expanded.value){
                Divider(color = main, thickness = 1.dp)

                val comment = remember{mutableStateOf(viewModel.getComment(item))}
                TextField(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    value = comment.value,
                    onValueChange = {
                        comment.value = it
                        viewModel.editComment(item, it)
                    },
                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                )

                Spacer(modifier = Modifier.height(3.dp))
            }
        }
    }
}

@Composable
private fun ItemInfoLine(item: Product, onDetailsAction: () -> Unit){
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

        RightLineElement(item, onDetailsAction)
    }
}

@Composable
private fun RightLineElement(item: Product, onDetailsAction: () -> Unit){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterEnd){
        Row {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "${item.price} $")

            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .size(30.dp)
                    .clickable { onDetailsAction() },
                elevation = 15.dp
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "...", fontSize = 25.sp)
                }
            }
        }
    }
}

private fun navigateToDetails(item: Product) {
    AppModule.navController.navigate(NavigationItem.DETAILS.title+"/${item.hashCode()}")
}