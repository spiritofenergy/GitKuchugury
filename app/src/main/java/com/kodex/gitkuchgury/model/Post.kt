package com.kodex.gitkuchgury.model

import androidx.room.Entity
import com.kodex.gitkuchgury.utils.Constants.Keys.POSTS_TABLE

@Entity(tableName = POSTS_TABLE)
data class Post(
    val id: Int,
    val image: String,
    val user: com.kodex.gitkuchgury.model.User,
    val isLiked: Boolean = false,
    val likesCount: Int,
    val commentsCount: Int,
    val timeStamp: Long
)


val names = arrayOf(
    "storee",
    "nianyc",
    "opioke",
    "ashoke",
    "dark_emarlds",
    "bedtan",
    "shrish",
    "matdo",
    "phillsohn",
    "deitch"
)