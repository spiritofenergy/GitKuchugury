package com.kodex.gitkuchgury.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kodex.gitkuchgury.utils.Constants.Keys.NOTES_TABLE

@Entity(tableName = NOTES_TABLE)
class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String = "",
    @ColumnInfo
    val subtitle: String = "",

    val firebaseId: String = ""
)