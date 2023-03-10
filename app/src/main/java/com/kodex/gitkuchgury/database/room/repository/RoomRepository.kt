package com.kodex.gitkuchgury.database.room.repository

import androidx.lifecycle.LiveData
import com.kodex.gitkuchgury.database.DatabaseRepository
import com.kodex.gitkuchgury.database.room.dao.NoteRoomDao
import com.kodex.gitkuchgury.model.Note

class RoomRepository(private val noteRoomDao: NoteRoomDao): DatabaseRepository {
    override val readAll: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
    }
}