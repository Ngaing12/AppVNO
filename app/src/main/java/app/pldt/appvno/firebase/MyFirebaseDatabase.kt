package app.pldt.appvno.firebase

import android.app.AlertDialog
import android.content.Context
import app.pldt.appvno.ui.call.CallDetailActivity
import com.google.firebase.database.*
import org.jetbrains.anko.startActivity


const val CALL_RINGING = "CALL_RINGING"
const val CALL_CALLING = "CALL_CALLING"
const val CALL_CONNECTED = "CALL_CONNECTED"

object MyFirebaseDatabase {
    private lateinit  var mContext: Context
    lateinit var userRef  : DatabaseReference
    operator fun invoke(context: Context) {
        mContext = context
    }

    fun startListening(uid : String){
        userRef = FirebaseDatabase.getInstance().reference.child("users").child(uid)
        userRef.addValueEventListener(childEventListener)
    }

    fun removeListener () {
        userRef.removeEventListener(childEventListener)
    }

    val childEventListener  = object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            if (p0.child("calling").exists()) {
                // check value
                val callState = p0.child("calling").child("callState").getValue().toString()
                val uid = p0.child("calling").child("uid").getValue().toString()
                if (callState == CALL_RINGING) {
                    // Display AlertDialog
                }
            }
        }

    }

//
//    private fun displayIncomingCallAlert(){
//        AlertDialog.Builder(mContext)
//            .setTitle("Information")
//            .setPositiveButton("Call") { _, _ ->
//               acceptCall()
//                activity?.startActivity<CallDetailActivity>()
//                // Todo - create node for calling for both user and status to  (Calling,Ringing)
//                // activity?.toast(row.email)
////                            // activity?.startActivity<MessageDetailActivity>(CONTACT_INFO to row.user)
//
//            }
//            .setNegativeButton("Cancel") { dialog, _ ->
//                dialog.cancel()
//            }
//            .setCancelable(true)
//            .setMessage(string)
//            .show()
//    }
//
//
//    val ref = FirebaseDatabase.getInstance().getReference("/users")
//    ref.addListenerForSingleValueEvent(object : ValueEventListener {
//        override fun onDataChange(p0: DataSnapshot) {
//            chatPartnerUser = p0.getValue(User::class.java)
//            viewHolder.itemView.tv_username_recyclerLatestMessage.text = chatPartnerUser?.username
//
//            Picasso.get().load(chatPartnerUser?.profileImageUrl).into(viewHolder.itemView.img_photo_recyclerLatestMessage)
//        }
//
//        override fun onCancelled(p0: DatabaseError) {
//        }
//    })
}


//object FireStoreUtil {
//    private val firestoreInstance : FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
//
//    private val currentUserDocRef : DocumentReference
//        get() = firestoreInstance.document("users/${FirebaseAuth.getInstance().uid
//            ?: throw NullPointerException("UID is null.")}")
//
//    private val chatChannelsCollectionRef = firestoreInstance.collection("chatChannels")
//
//
//    fun initCurrentUserIfFirstTime(onComplete: () -> Unit) {
//        currentUserDocRef.get().addOnSuccessListener {
//            if (!it.exists()){
//                val newUser = User(FirebaseAuth.getInstance().currentUser?.displayName ?: "",
//                    "",
//                    null,
//                    mutableListOf())
//                currentUserDocRef.set(newUser).addOnSuccessListener {
//                    onComplete()
//                }
//                    .addOnFailureListener {
//                        Log.d("Test", "Error saving ${it.message}")
//                    }
//            }
//            else
//                onComplete()
//        }
//    }
//
//    fun updateCurrentUser(name: String = "", bio : String = "", profilePicturePath: String? = null){
//        val userFieldMap = mutableMapOf<String, Any>()
//        if (name.isNotBlank()) userFieldMap["name"] = name
//        if (bio.isNotBlank()) userFieldMap["bio"] = bio
//        if (profilePicturePath != null)
//            userFieldMap["profilePicturePath"] = profilePicturePath
//        currentUserDocRef.update(userFieldMap)  .addOnFailureListener {
//            Log.d("Test", "Error saving ${it.message}")
//        }
//    }
//
//    fun getCurrentUser(onComplete: (User) -> Unit) {
//        currentUserDocRef.get()
//            .addOnSuccessListener {
//                it.toObject(User::class.java)?.let { user -> onComplete(user) }
//
//            }
//            .addOnFailureListener {
//                Log.d("Test", "Error saving ${it.message}")
//            }
//    }
//
//    fun addUserListener(context: Context, onListen: (List<Item>) -> Unit ) : ListenerRegistration {
//        return firestoreInstance.collection("users")
//            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                if (firebaseFirestoreException != null) {
//                    Log.e("FIRESTORE", "Users Listener error.", firebaseFirestoreException)
//                    return@addSnapshotListener
//                }
//                val items = mutableListOf<Item>()
//                querySnapshot?.documents?.forEach {
//                    if (it.id != FirebaseAuth.getInstance().currentUser?.uid)
//                        items.add(PersonItem(it.toObject(User::class.java)!!, it.id, context))
//                }
//                onListen(items)
//            }
//    }
//
//    fun removeListener(registration: ListenerRegistration) = registration.remove()
//
//    fun getOrCreateChatChannel(otherUserId : String,
//                               onComplete: (channelId : String) -> Unit) {
//        currentUserDocRef.collection("engageChatChannels")
//            .document(otherUserId).get().addOnSuccessListener {
//                if (it.exists()) {
//                    onComplete(it["channelId"] as String)
//                    return@addOnSuccessListener
//                }
//
//                val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
//
//                val newChannel = chatChannelsCollectionRef.document()
//                if (currentUserId != null){
//                    newChannel.set(ChatChannel(mutableListOf(currentUserId, otherUserId)))
//
//                    currentUserDocRef
//                        .collection("engageChatChannels")
//                        .document(otherUserId)
//                        .set(mapOf("channelId" to newChannel.id))
//
//                    firestoreInstance.collection("users").document(otherUserId)
//                        .collection("engageChatChannels")
//                        .document(currentUserId)
//                        .set(mapOf("channelId" to newChannel.id))
//                    onComplete(newChannel.id)
//                }
//            }
//    }
//
//    fun addChatMessagesListener(channelId : String, context : Context,
//                                onListen: (List<Item>) -> Unit) : ListenerRegistration {
//        return chatChannelsCollectionRef.document(channelId).collection("messages")
//            .orderBy("time")
//            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                if (firebaseFirestoreException != null){
//                    Log.e("FIRESTORE", "ChatMessage Listener error.", firebaseFirestoreException)
//                    return@addSnapshotListener
//                }
//                val items = mutableListOf<Item>()
//                querySnapshot?.documents?.forEach {
//                    if (it["type"] == MessageType.TEXT)
//                        items.add(TextMessageItem(it.toObject(TextMessage::class.java)!!, context))
//                    else
//                        items.add(ImageMessageItem(it.toObject(ImageMessage::class.java)!!, context))
//                }
//                onListen(items)
//            }
//    }
//
//    fun sendMessage(message : Message, channelId: String) {
//        chatChannelsCollectionRef.document(channelId)
//            .collection("messages")
//            .add(message)
//    }
//
//    //region FCM
//    fun getFCMRegistrationTokens(onComplete: (tokens: MutableList<String>) -> Unit) {
//        currentUserDocRef.get().addOnSuccessListener {
//            val user = it.toObject(User::class.java)
//            user?.registrationTokens?.let { tokens -> onComplete(tokens) }
//        }
//    }
//
//    fun setFCMRegistrationTokens(registrationTokens: MutableList<String>){
//        currentUserDocRef.update(mapOf("registrationTokens" to registrationTokens))
//    }
//
//    //endregion FCM
//}