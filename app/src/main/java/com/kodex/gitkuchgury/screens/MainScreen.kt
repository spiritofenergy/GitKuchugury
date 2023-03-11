package com.kodex.gitkuchgury.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.kodex.gitkuchgury.MainViewModel
import com.kodex.gitkuchgury.R
import com.kodex.gitkuchgury.model.currentUser
import com.kodex.gitkuchgury.ui.components.bottomBarHeight
import com.kodex.gitkuchgury.ui.components.icon

@ExperimentalFoundationApi
@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {

    val sectionState = remember { mutableStateOf(HomeSection.Home) }

    val navItems = HomeSection.values()
        .toList()
    Scaffold(
        bottomBar = {
            BottomBar(
                items = navItems,
                currentSection = sectionState.value,
                onSectionSelected = { sectionState.value = it},
            )
        }) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Crossfade(
            modifier = modifier,
            targetState = sectionState.value)
        { section ->
            when (section) {
                HomeSection.Home -> HomeScreen(navController = navController, viewModel = viewModel())
                HomeSection.Reels -> HomeScreen(navController = navController, viewModel = viewModel())
                HomeSection.Add -> Content(title = "Add Post options")
                HomeSection.Favorite -> Content(title = "Favorite")
                HomeSection.Profile -> Content(title = "Profile")

            }
        }
    }
}

@Composable
private fun Content(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
private fun BottomBar(
    items: List<HomeSection>,
    currentSection: HomeSection,
    onSectionSelected: (HomeSection) -> Unit,
) {
    BottomNavigation(
        modifier = Modifier.height(bottomBarHeight),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = contentColorFor(MaterialTheme.colors.background)
    ) {
        items.forEach { section ->

            val selected = section == currentSection

            val iconRes = if (selected) section.selectedIcon else section.icon

            BottomNavigationItem(
                icon = {

                    if (section == HomeSection.Profile) {
                        BottomBarProfile(selected)
                    } else {
                        Icon(
                            ImageBitmap.imageResource(id = iconRes),
                            modifier = Modifier.icon(),
                            contentDescription = ""
                        )
                    }

                },
                selected = selected,
                onClick = { onSectionSelected(section) },
                alwaysShowLabel = false
            )
        }
    }
}

@Composable
private fun BottomBarProfile(isSelected: Boolean) {
    val shape = CircleShape

    val borderModifier = if (isSelected) {
        Modifier
            .border(
                color = Color.LightGray,
                width = 1.dp,
                shape = shape
            )
    } else Modifier

    val padding = if (isSelected) 3.dp else 0.dp

    Box(
        modifier = borderModifier
    ) {
        Box(
            modifier = Modifier
                .icon()
                .padding(padding)
                .background(color = Color.LightGray, shape = shape)
                .clip(shape)
        ) {
            Image(
                painter = rememberImagePainter(currentUser.image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

private enum class HomeSection(
    val icon: Int,
    val selectedIcon: Int
) {

    Home(R.drawable.ic_outlined_home, R.drawable.ic_filled_home),
    Reels(R.drawable.ic_outlined_reels, R.drawable.ic_filled_reels),
    Add(R.drawable.ic_outlined_add, R.drawable.ic_outlined_add),
    Favorite(R.drawable.ic_outlined_favorite, R.drawable.ic_filled_favorite),
    Profile(R.drawable.ic_outlined_reels, R.drawable.ic_outlined_reels)
}

