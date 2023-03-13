package com.kodex.gitkuchgury.utils

import androidx.compose.runtime.mutableStateOf
import com.kodex.gitkuchgury.database.DatabaseNoteRepository
import com.kodex.gitkuchgury.database.DatabasePostRepository

    const val TYPE_DATABASE = "type_database"

    const val POST_ROOM = "post_room"
    const val POST_FIREBASE = "post_firebase"

    const val TYPE_ROOM = "type_room"
    const val TYPE_FIREBASE = "type_firebase"

    const val FIREBASE_ID = "firebase_id"

    lateinit var REPOSITORY_POST: DatabasePostRepository
    lateinit var REPOSITORY_NOTE: DatabaseNoteRepository
    lateinit var LOGIN: String
    lateinit var PASSWORD: String
    var DB_NOTE_TYPE = mutableStateOf("")
    var DB_POST_TYPE = mutableStateOf("")

    object Constants{
        object Keys{

            const val REFERENCE ="https://gitkuchugury-default-rtdb.europe-west1.firebasedatabase.app/"
            const val NOTES_DATABASE ="notes_database"
            const val POSTS_TABLE ="posts_table"
            const val NOTES_TABLE ="notes_table"
            const val NOTE_TITLE ="Заметка"
            const val NOTE_SUBTITLE ="Подробнее"
            const val ADD_NOTE ="Добавить дело"
            const val SAFE_NOTE ="Сохранить дело"
            const val TITLE ="title"
            const val SUBTITLE ="subtitle"
            const val WAT_WELL_WE_YSE ="Что использовать?"
            const val ROOM_DATABASE ="Room_database"
            const val FIREBASE_DATABASE ="Firebase_database"
            const val ID ="Id"
            const val NOTE ="note"
            const val NONE ="none"
            const val UPDATE_NOTE ="Update note"
            const val DELETE ="Delete"
            const val NAV_BECK ="Nav beck"
            const val EDIT_NOTE ="Edit note"
            const val LOG_IN ="Log in"
            const val EMPTY =""
            const val SAFE_ROOM ="Сохранить в Room"
            const val SAFE_FIREBASE ="В Firebase"
            const val SING_IN ="Sign in"
            const val LOGIN_TEXT ="Логин"
            const val PASSWORD_TEX ="Пароль"
        }
        object Screens{
            const val SPLASH_SCREEN = "splash_screen"
            const val HOME_SCREEN = "home_screen"
            const val START_SCREEN = "start_screen"
            const val TYPE_SCREEN = "type_screen"
            const val FAVORITE_SCREEN = "favorite_screen"
            const val MAIN_SCREEN = "main_screen"
            const val ADD_SCREEN = "add_screen"
            const val NOTE_SCREEN = "note_screen"


        }
    }
