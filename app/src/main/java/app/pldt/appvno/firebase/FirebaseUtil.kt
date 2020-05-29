package app.pldt.appvno.firebase

import android.app.Activity
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.ValueEventListener





class FirebaseUtil {
    fun removeFireBaseChildListener(
        activity: Activity?,
        firebaseRequestModel: FirebaseRequestModel?) {
        if (firebaseRequestModel != null) {
            if (firebaseRequestModel.mListner != null && firebaseRequestModel.mQuery != null) {
                val childEventListener =
                    firebaseRequestModel.mListner as ChildEventListener
                firebaseRequestModel.mQuery.removeEventListener(childEventListener)
               // Glide.get(activity).clearMemory() //clear memory
            }
        }
    }

    fun removeFireBaseValueEventListener(
        activity: Activity?,
        firebaseRequestModel: FirebaseRequestModel?
    ) {
        if (firebaseRequestModel != null) {
            if (firebaseRequestModel.mListner != null && firebaseRequestModel.mQuery != null) {
                val valueEventListener =
                    firebaseRequestModel.mListner as ValueEventListener
                firebaseRequestModel.mQuery.removeEventListener(valueEventListener)
//                Glide.get(activity).clearMemory() //clear memory
            }
        }
    }
}