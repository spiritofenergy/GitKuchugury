package com.kodex.gitkuchgury.screens

import android.app.Application
import android.provider.ContactsContract
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import com.kodex.gitkuchgury.ui.theme.GitKuchguryTheme
import com.kodex.gitkuchgury.utils.Constants
import com.kodex.gitkuchgury.utils.Constants.Keys.NONE
import com.kodex.gitkuchgury.utils.Constants.Keys.NOTE_SUBTITLE
import com.kodex.gitkuchgury.utils.Constants.Keys.NOTE_TITLE

@Composable
fun NoteScreen(navController: NavController, viewModel: MainViewModel, noteId: String?) {

    val notes = viewModel.reedAllNotes().observeAsState().value
    val note = notes?.firstOrNull { it.id == noteId?.toInt() } ?: Note(
        title = NONE,
        subtitle = NONE)

    Scaffold(
        modifier = Modifier.fillMaxWidth()
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
                    text = note.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 32.dp)
                )
                Text(
                    text = note.subtitle,
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
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        NoteScreen(navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1")

    }
}