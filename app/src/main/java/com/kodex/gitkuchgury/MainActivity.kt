package com.kodex.gitkuchgury

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.kodex.gitkuchgury.navigation.NavRoute
import com.kodex.gitkuchgury.navigation.NotesNavHost
import com.kodex.gitkuchgury.ui.components.icon
import com.kodex.gitkuchgury.ui.theme.GitKuchguryTheme
import com.kodex.gitkuchgury.ui.components.TopAppBar
import com.kodex.gitkuchgury.utils.DB_TYPE

class MainActivity : ComponentActivity() {
    private lateinit var mAuth: FirebaseAuth

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
                            NotesNavHost(mViewModel, navController)
                       }
                   }
               )
               
            }
        }
    }
}