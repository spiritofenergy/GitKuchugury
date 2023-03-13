package com.kodex.gitkuchgury.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.kodex.gitkuchgury.navigation.NavRoute
import com.kodex.gitkuchgury.ui.theme.GitKuchguryTheme
import com.kodex.gitkuchgury.ui.components.TopAppBar
import com.kodex.gitkuchgury.utils.*
import com.kodex.gitkuchgury.utils.Constants.Keys.LOGIN_TEXT
import com.kodex.gitkuchgury.utils.Constants.Keys.LOG_IN
import com.kodex.gitkuchgury.utils.Constants.Keys.PASSWORD_TEX
import com.kodex.gitkuchgury.utils.Constants.Keys.SING_IN
import com.kodex.gitkuchgury.utils.Constants.Keys.WAT_WELL_WE_YSE
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StartScreen(navController: NavController, viewModel: MainViewModel) {

    val context = LocalContext.current

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    var login by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var password by remember { mutableStateOf(Constants.Keys.EMPTY) }

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
                    Text(text = LOG_IN,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(value = login,
                        onValueChange = { login = it },
                        label = { Text(text = LOGIN_TEXT) },
                        isError = login.isEmpty()
                    )
                    OutlinedTextField(value = password,
                        onValueChange = { password = it },
                        label = { Text(text = PASSWORD_TEX) },
                        isError = password.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            LOGIN = login
                            PASSWORD = password
                            viewModel.initDataBase(TYPE_FIREBASE) {
                                DB_NOTE_TYPE.value = TYPE_FIREBASE
                                Toast.makeText(context, "Вы успешно авторизовались с $login & $password",
                                    Toast.LENGTH_LONG).show()
                                navController.navigate(NavRoute.Type.route)
                            }
                        },
                        enabled = login.isNotEmpty() && password.isNotEmpty()
                    ) {
                        Text(text = SING_IN)
                    }
                }
            }
        }) {
        Scaffold(
            topBar = { TopAppBar(navController) },
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = WAT_WELL_WE_YSE)
                Button(
                    onClick = {
                        viewModel.initDataBase(TYPE_ROOM) {
                            DB_NOTE_TYPE.value = TYPE_ROOM
                            navController.navigate(NavRoute.Type.route)
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = TYPE_ROOM)
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show()
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = TYPE_FIREBASE)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PrevStartScreen(){
    GitKuchguryTheme() {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        StartScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}
