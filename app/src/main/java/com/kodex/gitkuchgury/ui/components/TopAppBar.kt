package com.kodex.gitkuchgury.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.kodex.gitkuchgury.R
import androidx.navigation.NavController
import com.kodex.gitkuchgury.navigation.NavRoute

@Composable
fun TopAppBar (navController: NavController){
    val context = LocalContext.current
   // val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
   //val navController = rememberNavController()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                ImageVector.vectorResource(id = R.drawable.title_topbar),
                contentDescription = ""
            )
        }
        Icon(
            ImageBitmap.imageResource(id = R.drawable.ic_dm),
            modifier = Modifier.icon(),
            contentDescription = ""
        )
       // if (DB_TYPE.value.isNotEmpty()){
            Icon(imageVector = Icons.Default.ExitToApp,
                contentDescription = "",
                modifier = Modifier.clickable {
                    navController.navigate(NavRoute.Main.route)
                }
            )

    }
}