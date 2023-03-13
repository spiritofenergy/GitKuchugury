package com.kodex.gitkuchgury.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kodex.gitkuchgury.MainViewModel
import com.kodex.gitkuchgury.navigation.NavRoute.Type.route
import com.kodex.gitkuchgury.screens.*
import com.kodex.gitkuchgury.utils.Constants.Keys.ID
import com.kodex.gitkuchgury.utils.Constants.Screens.ADD_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.FAVORITE_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.HOME_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.MAIN_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.NOTE_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.SPLASH_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.START_SCREEN
import com.kodex.gitkuchgury.utils.Constants.Screens.TYPE_SCREEN

sealed class  NavRoute(var route: String){
   object Splash: NavRoute(SPLASH_SCREEN)
   object Home: NavRoute(HOME_SCREEN)
   object Start: NavRoute(START_SCREEN)
    object Add : NavRoute(ADD_SCREEN)
    object Main: NavRoute(MAIN_SCREEN)
    object Type: NavRoute(TYPE_SCREEN)
    object Favorite: NavRoute(FAVORITE_SCREEN)
    object Note: NavRoute(NOTE_SCREEN)
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun NotesNavHost(mViewModel: MainViewModel, navController: NavHostController) {


    NavHost(navController = navController, startDestination = NavRoute.Splash.route){
       composable(route = NavRoute.Splash.route){ SplashScreen(navController = navController, viewModel = mViewModel) }
       composable(route = NavRoute.Home.route){ HomeScreen(navController = navController, viewModel = mViewModel)}
       composable(route = NavRoute.Start.route){ StartScreen(navController = navController, viewModel = mViewModel)}
       composable(route = NavRoute.Add.route){ AddScreen(navController = navController, viewModel = mViewModel)}
       composable(route = NavRoute.Main.route){ MainScreen(navController = navController, viewModel = mViewModel)}
       composable(route = NavRoute.Type.route){ TypeScreen(navController = navController, viewModel = mViewModel)}
       composable(route = NavRoute.Favorite.route){ FavoriteScreen(navController = navController, viewModel = mViewModel)}
       composable(route = NavRoute.Note.route + "/{${ID}}"){backStackEntry -> NoteScreen(navController = navController,
           viewModel = mViewModel, noteId = backStackEntry.arguments?.getString(ID))}
    }
}