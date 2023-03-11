package com.kodex.gitkuchgury.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kodex.gitkuchgury.MainViewModel
import com.kodex.gitkuchgury.R.drawable
import com.kodex.gitkuchgury.database.room.repository.PostsRepository
import com.kodex.gitkuchgury.database.room.repository.StoriesRepository
import com.kodex.gitkuchgury.model.Post
import com.kodex.gitkuchgury.model.Story
import com.kodex.gitkuchgury.navigation.NavRoute
import com.kodex.gitkuchgury.ui.components.PostView
import com.kodex.gitkuchgury.ui.components.StoryImage
import com.kodex.gitkuchgury.ui.components.TopAppBar
import com.kodex.gitkuchgury.ui.components.icon
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(

        topBar = { TopAppBar(navController) }) {
        val posts by PostsRepository.posts
        val stories by StoriesRepository.observeStories()

        LazyColumn {
            item {
                StoriesSection(stories)
                Divider()
            }
            itemsIndexed(posts) { _, post ->
                Post(post,
                    onDoubleClick = {
                        coroutineScope.launch {
                            PostsRepository.performLike(post.id)
                        }
                    },
                    onLikeToggle = {
                        coroutineScope.launch {
                            PostsRepository.toggleLike(post.id)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun Toolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                ImageVector.vectorResource(id = com.kodex.gitkuchgury.R.drawable.title_topbar),
                contentDescription = ""
            )
        }
        Icon(
            ImageBitmap.imageResource(id = com.kodex.gitkuchgury.R.drawable.ic_dm),
            modifier = Modifier.icon(),
            contentDescription = ""
        )
    }
}

@Composable
private fun StoriesSection(stories: List<Story>) {
    Column {
        StoriesList(stories)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun StoriesList(stories: List<Story>) {
    LazyRow {
        itemsIndexed(stories) { index, story ->

            if (index == 0) {
                Spacer(modifier = Modifier.width(6.dp))
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 6.dp)
            ) {
                StoryImage(imageUrl = story.image)
                Spacer(modifier = Modifier.height(5.dp))
                Text(story.name, style = MaterialTheme.typography.caption)
            }

            if (index == stories.size.minus(1)) {
                Spacer(modifier = Modifier.width(6.dp))
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun Post(
    post: Post,
    onDoubleClick: (Post) -> Unit,
    onLikeToggle: (Post) -> Unit
) {
    PostView(post, onDoubleClick, onLikeToggle)
}
@Preview(showBackground = true)
@Composable
fun PrevHome(){


}
