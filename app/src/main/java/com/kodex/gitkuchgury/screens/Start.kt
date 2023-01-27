package com.kodex.gitkuchgury.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kodex.gitkuchgury.navigation.NavRoute

@Composable
fun StartScreen(navController: NavController) {
    
Scaffold(
    modifier = Modifier.fillMaxSize()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
) {
        Text(text = "What will we use?")
        Button(
            onClick = {
            navController.navigate(route = NavRoute.Main.route) },
            modifier = Modifier
                .width(200.dp)
                .padding(vertical = 8.dp)
            ) {
                Text(text = "Room")
        }
        Button(
            onClick = {
            navController.navigate(route = NavRoute.Add.route) },
            modifier = Modifier
                .width(200.dp)
                .padding(vertical = 8.dp)
            ) {
                Text(text = "Firebase")
        }
    }

}
}
@Preview(showBackground = true)
@Composable
fun PrevStartScreen(){
    StartScreen(navController = rememberNavController())

}