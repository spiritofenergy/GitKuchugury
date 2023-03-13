package com.kodex.gitkuchgury.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kodex.gitkuchgury.database.room.dao.NoteRoomDao
import com.kodex.gitkuchgury.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppRoomNoteDatabase: RoomDatabase() {

    abstract fun getRoomDao(): NoteRoomDao

    companion object{

        @Volatile
        private var INSTANCE_NOTE: AppRoomNoteDatabase? = null

        fun getInstance(context: Context): AppRoomNoteDatabase{
            return if (INSTANCE_NOTE == null){
                INSTANCE_NOTE = Room.databaseBuilder(
                    context,
                    AppRoomNoteDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANCE_NOTE as AppRoomNoteDatabase
            }else INSTANCE_NOTE as AppRoomNoteDatabase
        }
    }
}