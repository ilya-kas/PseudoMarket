package com.rubon.lab2.screen.profile

import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.maps.android.compose.*
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
                Modifier
                    .padding(3.dp)
                    .size(40.dp)
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

        LocationView()
    }
}

@Composable
private fun LocationView(){
    val isPermissionGranted = remember{mutableStateOf(false)}
    ComposablePermission(
        permission = android.Manifest.permission.ACCESS_FINE_LOCATION,
        onGranted = {isPermissionGranted.value = true}
    )

    Box(modifier = Modifier.padding(10.dp)){
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = MapProperties(isMyLocationEnabled = isPermissionGranted.value),
            uiSettings = MapUiSettings(myLocationButtonEnabled = isPermissionGranted.value)
        )
    }
}

/**
 * Composable helper for permission checking
 *
 * onDenied contains lambda for request permission
 *
 * @param permission permission for request
 * @param onGranted composable for [PackageManager.PERMISSION_GRANTED]
 * @param onDenied composable for [PackageManager.PERMISSION_DENIED]
 */
@Composable
fun ComposablePermission(
    permission: String,
    onGranted: @Composable () -> Unit
) {
    val ctx = LocalContext.current

    // check initial state of permission, it may be already granted
    var grantState by remember {
        mutableStateOf(ContextCompat.checkSelfPermission(ctx,permission) == PackageManager.PERMISSION_GRANTED)
    }

    if (grantState)
        onGranted()
    else {
        val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
                grantState = it
            }
        SideEffect {
            launcher.launch(permission)
        }
    }
}