package com.kodex.gitkuchgury

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.kodex.gitkuchgury.database.room.AppRoomDatabase
import com.kodex.gitkuchgury.database.room.repository.RoomRepository
import com.kodex.gitkuchgury.model.Note
import com.kodex.gitkuchgury.utils.REPOSITORY
import com.kodex.gitkuchgury.utils.TYPE_FIREBASE
import com.kodex.gitkuchgury.utils.TYPE_ROOM

/*
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kodex.gitkuchgury.database.firebase.AppFirebaseRepository
import com.kodex.gitkuchgury.database.room.AppRoomDatabase
import com.kodex.gitkuchgury.database.room.repository.RoomRepository
import com.kodex.gitkuchgury.model.Note
import com.kodex.gitkuchgury.utils.REPOSITORY
import com.kodex.gitkuchgury.utils.TYPE_FIREBASE
import com.kodex.gitkuchgury.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch*/

class MainViewModel (application: Application):AndroidViewModel(application){
    private val context = application

    fun initDataBase(type: String, onSuccess:()-> Unit){
        Log.d("check", "ManViewModel initDataBase with type : $type")
        when(type){
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
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
}
