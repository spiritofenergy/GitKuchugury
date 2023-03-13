package com.kodex.gitkuchgury.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kodex.gitkuchgury.model.Post

interface PostRoomDao {

    @Query("SELECT * FROM  posts_table")
    fun getAllPosts(): LiveData<List<Post>>

    @Insert
    suspend fun addPost(post:Post)

    @Update
    suspend fun updatePost(post: Post)

    @Delete
    suspend fun deletePost(post: Post)
}