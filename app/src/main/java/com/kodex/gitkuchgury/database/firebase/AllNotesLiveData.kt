package com.kodex.gitkuchgury.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kodex.gitkuchgury.model.Note
import com.kodex.gitkuchgury.utils.Constants
import com.kodex.gitkuchgury.utils.Constants.Keys.REFERENCE

class AllNotesLiveData : LiveData<List<Note>>() {
     private val mAuth = FirebaseAuth.getInstance()
     val database = Firebase.database(REFERENCE).reference
        .child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            // Создаем список наших заметок
            val notes = mutableListOf<Note>()
            snapshot.children.map {
                notes.add(it.getValue(Note::class.java) ?: Note())
            }
            value = notes
        }

        override fun onCancelled(error: DatabaseError) {}
    }

    override fun onActive() {
        database.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        database.removeEventListener(listener)
        super.onInactive()
    }
}