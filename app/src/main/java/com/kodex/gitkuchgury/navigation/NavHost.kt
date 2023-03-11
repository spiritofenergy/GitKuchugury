package com.kodex.gitkuchgury.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kodex.gitkuchgury.MainViewModel
import com.kodex.gitkuchgury.screens.*
import com.kodex.gitkuchgury.utils.Constants.Keys.ID
import com.kodex.gitkuchgury.utils.Constants.Screens.ADD_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.HOME_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.MAIN_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.NOTE_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.SPLASH_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.START_SCREEN

sealed class  NavRoute(val route: String){
   object Splash: NavRoute(SPLASH_SCREEN)
   object Home: NavRoute(HOME_SCREEN)
   object Start: NavRoute(START_SCREEN)
    object Add : NavRoute(ADD_SCREEN)
    object Main: NavRoute(MAIN_SCREEN)
    object Note: NavRoute(NOTE_SCREEN)
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun NotesNavHost(mViewModel: MainViewModel, navController: NavHostController) {


    NavHost(navController = navController, startDestination = NavRoute.Splash.route){
       composable(NavRoute.Splash.route){ SplashScreen(navController = navController, viewModel = mViewModel) }
       composable(NavRoute.Home.route){ HomeScreen(navController = navController, viewModel = mViewModel)}
       composable(NavRoute.Start.route){ StartScreen(navController = navController, viewModel = mViewModel)}
       composable(NavRoute.Add.route){ AddScreen(navController = navController, viewModel = mViewModel)}
       composable(NavRoute.Main.route){ MainScreen(navController = navController, viewModel = mViewModel)}
       composable(NavRoute.Note.route + "/{${ID}}"){backStackEntry -> NoteScreen(navController = navController,
           viewModel = mViewModel, noteId = backStackEntry.arguments?.getString(ID))}
    }
}