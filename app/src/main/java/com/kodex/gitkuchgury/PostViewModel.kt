package com.kodex.gitkuchgury

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.kodex.gitkuchgury.database.firebase.AppFirebaseRepository
import com.kodex.gitkuchgury.database.room.AppRoomPostDatabase
import com.kodex.gitkuchgury.database.room.repository.PostRoomRepository
import com.kodex.gitkuchgury.model.Post
import com.kodex.gitkuchgury.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application):AndroidViewModel(application) {
    private val context = application

        fun initDadaBase(type:String, onSuccess:()-> Unit){
            when(type){
                POST_ROOM -> {
                    val dao = AppRoomPostDatabase.getInstance(context = context).getPostDao()
                    Log.d("check", "PostViewModel initDataBase with: $dao")
                    REPOSITORY_POST = PostRoomRepository(dao)
                    Log.d("check", "PostViewModel initDataBase with: $REPOSITORY_POST")
                    onSuccess()
                }
                POST_FIREBASE -> {
                   // REPOSITORY_NOTE = AppFirebaseRepository()
                    REPOSITORY_POST.connectToFirebase(
                        {onSuccess() },
                        {Log.d("check", "Error: $it")}
                    )
                }
            }
        }
             fun addPost(post: Post,onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY_POST.create(post = post){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }
            fun updatePost(post: Post, onSuccess: () -> Unit){
                viewModelScope.launch(Dispatchers.IO){
                    REPOSITORY_POST.update(post = post){
                        viewModelScope.launch (Dispatchers.Main){
                            onSuccess()
                        }
                    }
                }
            }
            fun deletePost(post: Post, onSuccess: () -> Unit){
                viewModelScope.launch(Dispatchers.IO){
                    REPOSITORY_POST.delete(post = post){
                        viewModelScope.launch(Dispatchers.Main){
                            onSuccess()
                        }
                    }
                }
            }
            fun reedAllPosts() = REPOSITORY_POST.readAllPosts

    fun signOut(onSuccess: () -> Unit){
        when(DB_POST_TYPE.value){
            POST_FIREBASE,
            POST_ROOM -> {
                DB_POST_TYPE.value = Constants.Keys.EMPTY
                REPOSITORY_POST.signOut()
                onSuccess()
            }
            else -> {Log.d("check", "signOut: ELSE: ${DB_POST_TYPE.value}")}
        }
    }
}
class PostViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}