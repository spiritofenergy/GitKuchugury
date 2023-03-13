package com.kodex.gitkuchgury.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.kodex.gitkuchgury.MainViewModel
import com.kodex.gitkuchgury.ui.components.TopAppBar

@Composable
fun FavoriteScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { TopAppBar(navController)}
    ) {

    }
}