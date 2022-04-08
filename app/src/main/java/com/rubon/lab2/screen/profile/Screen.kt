package com.rubon.lab2.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
fun ProfileScreen(){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
            Image(
                painter = painterResource(id = R.drawable.photo),
                contentDescription = "light mod switch",
                Modifier.padding(3.dp).size(40.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.photo),
            contentDescription = "My photo",
            modifier = Modifier
                .padding(40.dp)
                .size(200.dp)
        )
        Column {
            Text(text = "Full name: Ilya Kasperski", fontSize = 30.sp)
            Text(text = "Mail: MyMail@gmail.com", fontSize = 30.sp)
        }
    }
}