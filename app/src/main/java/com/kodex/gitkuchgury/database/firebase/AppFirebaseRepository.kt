package com.kodex.gitkuchgury.database.firebase

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kodex.gitkuchgury.database.DatabaseRepository
import com.kodex.gitkuchgury.model.Note
import com.kodex.gitkuchgury.utils.Constants
import com.kodex.gitkuchgury.utils.Constants.Keys.REFERENCE
import com.kodex.gitkuchgury.utils.Constants.Keys.SUBTITLE
import com.kodex.gitkuchgury.utils.Constants.Keys.TITLE
import com.kodex.gitkuchgury.utils.FIREBASE_ID
import com.kodex.gitkuchgury.utils.LOGIN
import com.kodex.gitkuchgury.utils.PASSWORD

class AppFirebaseRepository() : DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database(REFERENCE).reference
        .child(mAuth.currentUser?.uid.toString())

    override val readAll: LiveData<List<Note>> = AllNotesLiveData()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteId = database.push().key.toString()
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.SUBTITLE] = note.subtitle


        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
            }
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {

    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun signOut() {
       mAuth.signOut()
    }

    override fun connectToFirebase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
                mAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener{onFail(it.message.toString())}
    }
}


}