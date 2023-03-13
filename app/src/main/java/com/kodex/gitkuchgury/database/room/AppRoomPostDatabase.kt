package com.kodex.gitkuchgury.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kodex.gitkuchgury.database.room.dao.PostRoomDao
import com.kodex.gitkuchgury.model.Post

@Database(entities = [Post::class], version = 1)
abstract class AppRoomPostDatabase: RoomDatabase() {

    abstract fun getPostDao(): PostRoomDao

    companion object{

        @Volatile
        private var INSTANCE_POST : AppRoomPostDatabase? = null

        fun getInstance(context: Context): AppRoomPostDatabase {
            return if (INSTANCE_POST == null){
                INSTANCE_POST = Room.databaseBuilder(
                    context,
                    AppRoomPostDatabase::class.java,
                    "posts_database"
                ).build()
                INSTANCE_POST as AppRoomPostDatabase
            }else INSTANCE_POST as AppRoomPostDatabase
        }
    }
}