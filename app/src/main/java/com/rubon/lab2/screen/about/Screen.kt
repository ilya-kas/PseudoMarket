package com.rubon.lab2.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rubon.lab2.R

@Preview(showBackground = true)
@Composable
fun AboutScreen(){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
            ){
        Image(
            painter = painterResource(id = R.drawable.photo),
            contentDescription = "My photo",
            modifier = Modifier
                .padding(40.dp)
                .size(200.dp)
        )
        Column {
            Text(text = "Author: Ilya Kasperski", fontSize = 30.sp)
            Text(text = "Group: 951008", fontSize = 30.sp)
            Text(text = "Lab: 2", fontSize = 30.sp)
        }
    }
}