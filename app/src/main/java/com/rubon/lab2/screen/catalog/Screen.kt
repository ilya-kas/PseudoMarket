package com.rubon.lab2.screen.catalog

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rubon.lab2.app_level.App
import com.rubon.lab2.app_level.dagger.module.AppModule
import com.rubon.lab2.logic.entity.FilterType
import com.rubon.lab2.logic.entity.Product
import com.rubon.lab2.screen.NavigationItem
import com.rubon.lab2.ui.theme.main

private val viewModel = App.appComponent.getCatalogViewModel()

@Composable
fun CatalogScreen() {
    viewModel.preload()
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar()
        Filters()
        ItemsList()
    }
}

@Composable
private fun SearchBar(){
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
                viewModel.addFilter(FilterType.MASK, it)
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
private fun Filters(){
    val expanded = remember {mutableStateOf(false)}

    Divider(color = main, thickness = 1.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .animateContentSize()
    ) {
        Row (modifier = Modifier.clickable { expanded.value = !expanded.value }){ //плашка с фильтрами в сжатом состоянии
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null
            )
            Text(text = "Filters")
        }
        if (expanded.value) {                                    // фильтры
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Category:")
                for (category in viewModel.getCategories()) {
                    val checkedStatus = remember { mutableStateOf(false) }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Checkbox(
                            checked = checkedStatus.value,
                            onCheckedChange = {
                                if (it)
                                    viewModel.addFilter(FilterType.CATEGORY, category)
                                else
                                    viewModel.removeFilter(FilterType.CATEGORY, category)
                                checkedStatus.value = it
                            },
                            colors = CheckboxDefaults.colors(main)
                        )
                        Text(text = category)
                    }
                }
                PriceFilter()
            }
        }
    }
    Divider(color = main, thickness = 1.dp)
}

@Preview(showBackground = true)
@Composable
private fun PriceFilter(){
    val fieldWidth = 100.dp
    val fieldHeight = 50.dp
    val from = remember{ mutableStateOf(0.0)}
    val to = remember{ mutableStateOf(0.0)}

    Text(text = "Price:")
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "From:")
        OutlinedTextField(
            modifier = Modifier.padding(horizontal = 10.dp).size(fieldWidth, fieldHeight),
            value = from.value.toString(),
            onValueChange = {
                viewModel.addFilter(FilterType.PRICE_MIN, it.toDouble())
                from.value = it.toDouble()
            },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = "To:")
        OutlinedTextField(
            modifier = Modifier.padding(horizontal = 10.dp).size(fieldWidth, fieldHeight),
            value = to.value.toString(),
            onValueChange = {
                viewModel.addFilter(FilterType.PRICE_MAX, it.toDouble())
                to.value = it.toDouble()
            },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
private fun ItemsList(){
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

private fun navigateToDetails(item: Product) {
    AppModule.navController.navigate(NavigationItem.DETAILS.title+"/${item.hashCode()}")
}