package com.kodex.gitkuchgury

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.kodex.gitkuchgury.database.firebase.AppFirebaseRepository
import com.kodex.gitkuchgury.database.room.AppRoomNoteDatabase
import com.kodex.gitkuchgury.database.room.repository.NoteRoomRepository
import com.kodex.gitkuchgury.model.Note
import com.kodex.gitkuchgury.utils.*
import com.kodex.gitkuchgury.utils.Constants.Keys.EMPTY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class MainViewModel (application: Application):AndroidViewModel(application){
    private val context = application

    fun initDataBase(type: String, onSuccess:()-> Unit){
        Log.d("check", "ManViewModel initDataBase with type : $type")
        when (type){

            TYPE_ROOM -> {

                val dao = AppRoomNoteDatabase.getInstance(context = context).getRoomDao()
                Log.d("check", "ManViewModel initDataBase with: $dao")
                REPOSITORY_NOTE = NoteRoomRepository(dao)
                Log.d("check", "ManViewModel initDataBase with: $REPOSITORY_NOTE")
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY_NOTE = AppFirebaseRepository()
                REPOSITORY_NOTE.connectToFirebase(
                    {onSuccess() },
                    {Log.d("check", "Error: $it")}
                )
            }
        }
    }
    fun addNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY_NOTE.create(note = note){
                viewModelScope.launch ( Dispatchers.Main ){
                    onSuccess()
                }
            }
        }
    }
    fun updateNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY_NOTE.update(note = note){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch (Dispatchers.IO){
            REPOSITORY_NOTE.delete(note = note){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun reedAllNotes() = REPOSITORY_NOTE.readAllNotes

    fun signOut(onSuccess: () -> Unit){
        when(DB_NOTE_TYPE.value){
            TYPE_FIREBASE,
                TYPE_ROOM -> {
                DB_NOTE_TYPE.value = EMPTY
                    REPOSITORY_NOTE.signOut()
                onSuccess()
                }
            else -> {Log.d("check", "signOut: ELSE: ${DB_NOTE_TYPE.value}")}
        }
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}