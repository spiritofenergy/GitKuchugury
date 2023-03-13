package com.kodex.gitkuchgury.database.room.repository

import androidx.lifecycle.LiveData
import com.kodex.gitkuchgury.database.DatabaseNoteRepository
import com.kodex.gitkuchgury.database.room.dao.NoteRoomDao
import com.kodex.gitkuchgury.model.Note

class NoteRoomRepository (private val noteRoomDao: NoteRoomDao): DatabaseNoteRepository{
    override val readAllNotes: LiveData<List<Note>>
        get() =  noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
       noteRoomDao.addNote(note = note)
        onSuccess()
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
        onSuccess()
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
       noteRoomDao.deleteNote(note = note)
        onSuccess()
    }

    override fun signOut() {
    }
}