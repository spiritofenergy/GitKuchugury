package com.kodex.gitkuchgury.screens

import android.app.Activity
import android.content.Intent
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.kodex.gitkuchgury.MainViewModel
import com.kodex.gitkuchgury.R
import com.kodex.gitkuchgury.navigation.NavRoute
import com.kodex.gitkuchgury.service.LoadService
import com.kodex.gitkuchgury.utils.Constants
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel) {
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val activity = if (context is Activity)context else null
    //val authValue = authViewModel.isUserAuthenticated
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
       // activity?.startService(Intent(context, LoadService::class.java))
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(durationMillis = 1500, easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            })
        )
        delay(1000)


        navController.navigate(NavRoute.Main.route){
            popUpTo(Constants.Screens.MAIN_SCREEN){
                inclusive = true
            }

        }

    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.title_topbar),
            contentDescription = "Splash Screen Logo", modifier = Modifier.scale(scale.value))
    }

}