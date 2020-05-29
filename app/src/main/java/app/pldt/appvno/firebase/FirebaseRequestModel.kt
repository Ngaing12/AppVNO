package app.pldt.appvno.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ServerValue

class FirebaseRequestModel {
    lateinit var mListner : Any
    lateinit var mQuery : Query

    constructor( listener : Any, query: Query) {
        mListner = listener
        mQuery = query
        val userRefUser = FirebaseDatabase.getInstance().reference.child("users").push().key
    }

}