package com.rubon.lab2.screen.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rubon.lab2.app_level.App
import com.rubon.lab2.data.entity.Product

internal val viewModel = App.appComponent.getCatalogViewModel()

@Preview(showBackground = true)
@Composable
fun CatalogScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar()
        Filters()
        ItemsList()
    }
}

@Composable
fun SearchBar(){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp, vertical = 5.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        val mask = remember{mutableStateOf("")}
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = mask.value,
            onValueChange = {
                mask.value = it
                viewModel.filter(it)
            },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
            trailingIcon = {Icon(
                Icons.Filled.Search,
                contentDescription = "search icon"
            )}
        )
    }
}

@Composable
fun Filters(){
    //todo
}

@Composable
fun ItemsList(){
    val filteredItems = viewModel.filteredProducts.observeAsState()

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
fun ItemLine(item: Product){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp, vertical = 5.dp),
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