package com.kodex.gitkuchgury.screens

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kodex.gitkuchgury.navigation.NavRoute
import com.kodex.gitkuchgury.navigation.NotesNavHost
import com.kodex.gitkuchgury.ui.theme.GitKuchguryTheme

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.Add.route)
                }) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = "Add Icons",
                    tint = Color.White)
            }
        }
    ){
        Column() {
            NoteItem(title = "Note 1", subtitle = "Subtitle for none 1", navController = navController)
            NoteItem(title = "Note 2", subtitle = "Subtitle for none 2", navController = navController)
            NoteItem(title = "Note 3", subtitle = "Subtitle for none 3", navController = navController)
            NoteItem(title = "Note 4", subtitle = "Subtitle for none 4", navController = navController)
            NoteItem(title = "Note 5", subtitle = "Subtitle for none 5", navController = navController)

        }

        }
    }



@Composable
fun NoteItem(title: String, subtitle: String,
                 navController: NavController
    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route)
            },
            elevation = 6.dp
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                )
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun PrevMainScreen(){
    GitKuchguryTheme() {
        MainScreen(navController = rememberNavController())
    }
}