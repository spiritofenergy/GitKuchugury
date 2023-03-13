package com.kodex.gitkuchgury.database.firebase

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kodex.gitkuchgury.database.DatabaseNoteRepository
import com.kodex.gitkuchgury.model.Note
import com.kodex.gitkuchgury.utils.Constants.Keys.REFERENCE
import com.kodex.gitkuchgury.utils.Constants.Keys.SUBTITLE
import com.kodex.gitkuchgury.utils.Constants.Keys.TITLE
import com.kodex.gitkuchgury.utils.FIREBASE_ID
import com.kodex.gitkuchgury.utils.LOGIN
import com.kodex.gitkuchgury.utils.PASSWORD

class AppFirebaseRepository() : DatabaseNoteRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database(REFERENCE).reference
        .child(mAuth.currentUser?.uid.toString())

    override val readAllNotes: LiveData<List<Note>> = AllNotesLiveData()

        //Создаем заметку
    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteId = database.push().key.toString()
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[TITLE] = note.title
        mapNotes[SUBTITLE] = note.subtitle

        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{

            }
    }

    @SuppressLint("SuspiciousIndentation")
    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        val noteId = note.firebaseId
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[TITLE] = note.title
        mapNotes[SUBTITLE] = note.subtitle

             database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
            }

    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
       database.child(note.firebaseId).removeValue()
           .addOnSuccessListener { onSuccess() }
           .addOnFailureListener{}
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