package com.kodex.gitkuchgury.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kodex.gitkuchgury.ui.theme.GitKuchguryTheme

@Composable
fun NoteScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxWidth()
    ) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Title",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 32.dp)
                )
                Text(
                    text = "Subtitle",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(top = 22.dp)
                )
            }
        }
    }
    }
}
@Preview(showBackground = true)
@Composable
fun PrevNoteScreen(){
    GitKuchguryTheme() {
        NoteScreen(navController = rememberNavController())

    }
}