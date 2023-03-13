package com.kodex.gitkuchgury.database.room.repository

import androidx.lifecycle.LiveData
import com.kodex.gitkuchgury.database.DatabasePostRepository
import com.kodex.gitkuchgury.database.room.dao.PostRoomDao
import com.kodex.gitkuchgury.model.Post


class PostRoomRepository (private val postRoomDao: PostRoomDao): DatabasePostRepository{
    override val readAllPosts: LiveData<List<Post>>
        get() = postRoomDao.getAllPosts()

    override suspend fun create(post: Post, onSuccess: () -> Unit) {
       postRoomDao.addPost(post = post)
        onSuccess()
    }

    override suspend fun update(post: Post, onSuccess: () -> Unit) {
        postRoomDao.updatePost(post = post)
        onSuccess()
    }

    override suspend fun delete(post: Post, onSuccess: () -> Unit) {
        postRoomDao.deletePost(post = post)
        onSuccess()
    }
    override fun signOut(){

    }
}