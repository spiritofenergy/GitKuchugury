package com.kodex.gitkuchgury

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.kodex.gitkuchgury.navigation.NotesNavHost
import com.kodex.gitkuchgury.ui.theme.GitKuchguryTheme

class MainActivity : ComponentActivity() {
    private lateinit var mAuth: FirebaseAuth

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GitKuchguryTheme {
                val context = LocalContext.current
                val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                val navController = rememberNavController()
                Scaffold (
                 //  topBar = { TopAppBar() },
                   content = {
                       Surface(modifier = Modifier.fillMaxSize(),
                           color = MaterialTheme.colors.background) {
                          // MainScreen(navController = navController, viewModel = viewModel() )
                            NotesNavHost(mViewModel, navController)
                       }
                   }
               )
               
            }
        }
    }
}