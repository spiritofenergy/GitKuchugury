package com.kodex.gitkuchgury.database

import androidx.lifecycle.LiveData
import com.kodex.gitkuchgury.model.Post

interface DatabasePostRepository {

        val readAllPosts: LiveData<List<Post>>

        suspend fun create(post: Post, onSuccess:()->Unit)

        suspend fun update(post: Post, onSuccess:()->Unit)

        suspend fun delete(post: Post, onSuccess:()->Unit)

        fun signOut(){}

        fun connectToFirebase(onSuccess: () -> Unit, onFail: (String)-> Unit){}
}