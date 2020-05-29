package app.pldt.appvno.firebase

import app.pldt.appvno.common.FIREBASE_SUCCESS
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference


abstract class MyFirebaseRealtimeDb {


    /**
     * Insert data on FireBase
     *
     * @param databaseReference Database reference of data to be add
     * @param model Model to insert into database
     * @param callback callback for event handling
     */
    protected fun fireBaseCreate(
        databaseReference: DatabaseReference,
        model: Any?,
        callback: FirebaseCallback
    ) {
        databaseReference.keepSynced(true)
        databaseReference.setValue(model) { databaseError, _ ->
            if (databaseError == null) {
                callback.onSuccess(FIREBASE_SUCCESS)
            } else {
                callback.onError(databaseError)
            }
        }
    }


    /**
     * Update data to FireBase
     *
     * @param databaseReference  Database reference of data to update
     * @param map Data map to update
     * @param callback callback for event handling
     */
    protected fun fireBaseUpdateChildren(
        databaseReference: DatabaseReference,
        map: Map<String, *>?,
        callback: FirebaseCallback
    ) {
        databaseReference.keepSynced(true)
        map?.let {
            databaseReference.updateChildren(
                it,
                DatabaseReference.CompletionListener { databaseError, _ ->
                    if (databaseError == null) {
                        callback.onSuccess(databaseError)
                    } else {
                        callback.onError(databaseError)
                    }
                })
        }
    }


    /**
     * Delete data from firebase
     *
     * @param databaseReference  Database reference of data to delete
     * @param callback callback for event handling
     */
    protected fun fireBaseDelete(
        databaseReference: DatabaseReference,
        callback: FirebaseCallback
    ) {
        databaseReference.keepSynced(true)
        databaseReference.removeValue { databaseError, _ ->
            if (databaseError == null) {
                callback.onSuccess(null)
            } else {
                callback.onError(databaseError)
            }
        }
    }

    /**
     * Getting data from FireBase only single time
     *
     * @param query  query of database reference to fetch data
     * @param callback  callback for event handling
     */
    protected fun fireBaseReadData(query: Query, callback: FirebaseCallback) {
        query.keepSynced(true)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback.onSuccess(dataSnapshot)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onError(databaseError)
            }
        })
    }


    /**
     * Fetch data with child event listener
     *
     * @param query    to add childEvent listener
     * @param firebaseChildCallBack  callback for event handling
     * @return ChildEventListener
     */
    protected fun fireBaseChildEventListener(
        query: Query,
        firebaseChildCallBack: FirebaseChildCallBack
    ): ChildEventListener {
        query.keepSynced(true)
        return object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                firebaseChildCallBack.onChildAdded(dataSnapshot)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                firebaseChildCallBack.onChildChanged(dataSnapshot)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                firebaseChildCallBack.onChildRemoved(dataSnapshot)
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
                firebaseChildCallBack.onChildMoved(dataSnapshot)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                firebaseChildCallBack.onCancelled(databaseError)
            }
        }
    }


    /**
     * Fetch data with Value event listener
     *
     * @param query    to add childEvent listener
     * @param callback  callback for event handling
     * @return ValueEventListener reference
     */
    protected fun fireBaseDataChangeListener(query: Query, callback: FirebaseCallback): ValueEventListener {
        query.keepSynced(true)
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback.onSuccess(dataSnapshot)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onError(databaseError)
            }
        }
        query.addValueEventListener(valueEventListener)
        return valueEventListener
    }

    /**
     * Insert offline data on FireBase
     *
     * @param databaseReference  Database reference of data to create
     * @param model Model to insert into database
     */
    protected fun fireBaseOfflineCreate(
        databaseReference: DatabaseReference,
        model: Any?
    ) {
        try {
            databaseReference.keepSynced(true)
            databaseReference.setValue(model)
        } catch (e: Exception) {
//            ExceptionUtil.errorMessage(
//                "Method: fireBaseOfflineCreateAndUpdate",
//                "Class: FirebaseTemplateRepository",
//                e
//            )
        }
    }


    /**
     * update offline data on FireBase
     *
     * @param databaseReference  Database reference of data to update
     * @param model Model to update into database
     */
    protected fun fireBaseOfflineUpdate(
        databaseReference: DatabaseReference,
        pushKey: String,
        model: Any
    ) {
        try {
            databaseReference.keepSynced(true)
            val stringObjectMap: MutableMap<String, Any> =
                HashMap()
            stringObjectMap[pushKey] = model
            databaseReference.updateChildren(stringObjectMap)
        } catch (e: java.lang.Exception) {
//            ExceptionUtil.errorMessage(
//                "Method: fireBaseOfflineCreateAndUpdate",
//                "Class: FirebaseTemplateRepository",
//                e
//            )
        }
    }


}