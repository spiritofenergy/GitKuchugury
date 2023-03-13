package com.kodex.gitkuchgury.screens

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kodex.gitkuchgury.MainViewModel
import com.kodex.gitkuchgury.MainViewModelFactory
import com.kodex.gitkuchgury.model.Note
import com.kodex.gitkuchgury.navigation.NavRoute
import com.kodex.gitkuchgury.ui.components.TopAppBar
import com.kodex.gitkuchgury.ui.theme.GitKuchguryTheme
import com.kodex.gitkuchgury.utils.Constants.Keys.ADD_NOTE
import com.kodex.gitkuchgury.utils.Constants.Keys.NOTE_SUBTITLE
import com.kodex.gitkuchgury.utils.Constants.Keys.NOTE_TITLE

@Composable
fun AddScreen(navController: NavController, viewModel: MainViewModel) {

    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

Scaffold(
    topBar = { TopAppBar(navController) },
) {
    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Text(text = "Add new note",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
                isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()},
            label = { Text(text = NOTE_TITLE)},
            isError = title.isEmpty()
        )
        OutlinedTextField(
            value = subtitle,
            onValueChange = {
                subtitle = it
                isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()},
            label = { Text(text = NOTE_SUBTITLE)},
            isError = subtitle.isEmpty()
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            enabled = isButtonEnabled,
            onClick = {
                navController.navigate(route = NavRoute.Type.route)
                viewModel.addNote(note = Note(title = title, subtitle = subtitle)){
                    Log.d("check", "Title: $title, Subtitle: $subtitle")
                }
        }) {
            Text(text = ADD_NOTE)
        }

    }
}
}
@Preview(showBackground = true)
@Composable
fun PrevAddScreen(){
    GitKuchguryTheme() {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        AddScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}