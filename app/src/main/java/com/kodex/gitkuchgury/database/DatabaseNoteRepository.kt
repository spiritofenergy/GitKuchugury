package com.kodex.gitkuchgury.database

import androidx.lifecycle.LiveData
import com.kodex.gitkuchgury.model.Note

interface DatabaseNoteRepository {

    val readAllNotes: LiveData<List<Note>>
   // val readAllPost: LiveData<List<Post>>

    suspend fun create(note: Note, onSuccess: ()->Unit)

    suspend fun update(note: Note, onSuccess: ()->Unit)

    suspend fun delete(note: Note, onSuccess: ()->Unit)

    fun signOut(){}

    fun connectToFirebase(onSuccess: () -> Unit, onFail:(String) -> Unit){}
}