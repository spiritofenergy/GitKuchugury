package com.kodex.gitkuchgury

import android.graphics.fonts.FontFamily
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kodex.gitkuchgury.navigation.NotesNavHost
import com.kodex.gitkuchgury.ui.theme.GitKuchguryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitKuchguryTheme {
               Scaffold (
                   topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Git Kuchugury") },
                                backgroundColor = Color.Blue,
                                contentColor = Color.White,
                                elevation = 12.dp
                            )
                   },
                   content = {
                       Surface(modifier = Modifier.fillMaxSize(),
                           color = MaterialTheme.colors.background) {
                            NotesNavHost()
                       }
                   }
               )
               
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GitKuchguryTheme {
        Greeting("Android")
    }
}