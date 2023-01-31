package com.kodex.gitkuchgury.database.firebase

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.kodex.gitkuchgury.database.DatabaseRepository
import com.kodex.gitkuchgury.model.Note
import com.kodex.gitkuchgury.utils.LOGIN
import com.kodex.gitkuchgury.utils.PASSWORD

class AppFirebaseRepository() : DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    override val readAll: LiveData<List<Note>>
        get() = TODO("Not yet implemented")

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun signOut() {
       mAuth.signOut()
    }

    override fun connectionToFirebase(onSuccess: () -> Unit, onFail:(String) -> Unit) {
        mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
                mAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener{onFail(it.message.toString())}
    }
}


}