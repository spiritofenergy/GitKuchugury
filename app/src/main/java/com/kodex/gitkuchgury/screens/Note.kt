package com.kodex.gitkuchgury.screens

import android.app.Application
import android.provider.ContactsContract
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.kodex.gitkuchgury.navigation.NavRoute
import com.kodex.gitkuchgury.ui.theme.GitKuchguryTheme
import com.kodex.gitkuchgury.utils.Constants
import com.kodex.gitkuchgury.utils.Constants.Keys.DELETE
import com.kodex.gitkuchgury.utils.Constants.Keys.EDIT_NOTE
import com.kodex.gitkuchgury.utils.Constants.Keys.EMPTY
import com.kodex.gitkuchgury.utils.Constants.Keys.NAV_BECK
import com.kodex.gitkuchgury.utils.Constants.Keys.NONE
import com.kodex.gitkuchgury.utils.Constants.Keys.NOTE_SUBTITLE
import com.kodex.gitkuchgury.utils.Constants.Keys.NOTE_TITLE
import com.kodex.gitkuchgury.utils.Constants.Keys.SUBTITLE
import com.kodex.gitkuchgury.utils.Constants.Keys.TITLE
import com.kodex.gitkuchgury.utils.Constants.Keys.UPDATE_NOTE
import com.kodex.gitkuchgury.utils.DB_TYPE
import com.kodex.gitkuchgury.utils.TYPE_FIREBASE
import com.kodex.gitkuchgury.utils.TYPE_ROOM
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavController, viewModel: MainViewModel, noteId: String?) {

    val notes = viewModel.reedAllNotes().observeAsState().value
    val note = when(DB_TYPE){
        TYPE_ROOM ->{
            notes?.firstOrNull{ it.id == noteId?.toInt()} ?: Note()
        }
        TYPE_FIREBASE ->{
            notes?.firstOrNull{ it.firebaseId  == noteId} ?: Note()
        }
        else -> Note()
    }
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    var title by remember { mutableStateOf(EMPTY) }
    var subtitle by remember { mutableStateOf(EMPTY) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface() {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 32.dp)
                ) {
                    Text(text = EDIT_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(value = title,
                        onValueChange = { title = it },
                        label = { Text(text = TITLE) },
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(value = subtitle,
                        onValueChange = { subtitle = it },
                        label = { Text(text = SUBTITLE) },
                        isError = subtitle.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                        viewModel.updateNote(note =
                        Note(id = note.id, title = title, subtitle = subtitle, firebaseId = note.firebaseId)){
                            navController.navigate(NavRoute.Main.route)
                        } }) {
                        Text(text = UPDATE_NOTE)
                    }
                }

            }
        }) {
        Scaffold(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
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

                        Button(modifier = Modifier.padding(vertical = 16.dp),
                            onClick = {
                                coroutineScope.launch{
                                    title = note.title
                                    subtitle = note.subtitle
                                    bottomSheetState.show()
                                }
                            }) {
                            Text(text = UPDATE_NOTE)
                        }
                        Button(onClick = {
                           viewModel.deleteNote(note = note){
                               navController.navigate(NavRoute.Main.route)
                           }
                        }) {
                            Text(text = DELETE)
                        }
                        Button(onClick = {
                            navController.navigate(NavRoute.Main.route)
                        }) {
                            Text(text = NAV_BECK)
                        }
                    }
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