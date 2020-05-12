package app.pldt.appvno.ui.message

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import app.pldt.appvno.R
import app.pldt.appvno.model.ChatMessage
import app.pldt.appvno.model.Contact
import app.pldt.appvno.model.TempUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_message_detail.*
import kotlinx.android.synthetic.main.recycler_chat_from_row_app.view.*
import kotlinx.android.synthetic.main.recycler_chat_from_row_sms.view.*
import kotlinx.android.synthetic.main.recycler_chat_to_row_app.view.*
import kotlinx.android.synthetic.main.recycler_chat_to_row_sms.view.*

class MessageDetailActivity : AppCompatActivity() {

    private lateinit var user : TempUser
    var message : String? = ""
    var messageType : Int? = 0

    private var fromId : String?  = null
    private var toId = ""
    val adapter = GroupAdapter<GroupieViewHolder>()

    lateinit var listener : ChildEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_detail)

        user = intent.getSerializableExtra(CONTACT_INFO) as TempUser

        tv_user_messageDetail.text = user.email
        tv_number_messageDetail.text = user.number

        recycler_chat_messageDetail.adapter = adapter

        img_back_messageDetail.setOnClickListener {
            finish()
        }

        listenForMessage()
        img_send_messageDetail.setOnClickListener {
            sendMessage()
        }
    }


    private fun listenForMessage() {

        fromId = FirebaseAuth.getInstance().uid
        toId = user.id

        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")

        listener =  ref.addChildEventListener(object: ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {}
            override fun onChildChanged(p0: DataSnapshot, p1: String?) {}

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)
                chatMessage?.let {
                    if (it.fromId == FirebaseAuth.getInstance().uid){
                        adapter.add(ChatToApp(it.message))
                    }
                    else {
                        adapter.add(ChatFromApp(it.message))
                    }

                }
                recycler_chat_messageDetail.scrollToPosition(adapter.itemCount -1)
            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")
            ref.removeEventListener(listener)
    }

    private fun sendMessage() {
        // Send message to firebase
        // TODO - error double  message if chating to your self

        if (edt_content_messageDetail.text.toString().isEmpty()) return

        val message = edt_content_messageDetail.text.toString()

        if (fromId == null) return
        val ref =  FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()
        val toRef =  FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()
      //  val latestMessageRef =  FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId/$toId")
       // val latestMessageToRef =  FirebaseDatabase.getInstance().getReference("/latest-messages/$toId/$fromId")
        val chatMessage  = ChatMessage(ref.key!!,message,
            fromId!!,toId, System.currentTimeMillis() / 1000, 0)
        ref.setValue(chatMessage)
            .addOnSuccessListener {
                Toast.makeText(this, "Successfully send", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error send", Toast.LENGTH_SHORT).show()
            }

        toRef.setValue(chatMessage)
            .addOnSuccessListener {
                edt_content_messageDetail.text.clear()
                recycler_chat_messageDetail.scrollToPosition(adapter.itemCount -1)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error send", Toast.LENGTH_SHORT).show()
            }

//        latestMessageRef.setValue(chatMessage)
//            .addOnFailureListener {
//                Toast.makeText(this, "Error send", Toast.LENGTH_SHORT).show()
//            }
//
//        latestMessageToRef.setValue(chatMessage)
//            .addOnFailureListener {
//                Toast.makeText(this, "Error send", Toast.LENGTH_SHORT).show()
//            }
    }

}


class ChatFromSMS (val message: String): Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.recycler_chat_from_row_sms
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_message_from_to_sms.text = message
    }
}


class ChatFromApp (val message: String): Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.recycler_chat_from_row_app
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_message_from_app.text = message
    }
}


class ChatToSMS (val message: String): Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.recycler_chat_to_row_sms
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_message_chat_to_sms.text = message
    }

}


class ChatToApp (val message: String): Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.recycler_chat_to_row_app
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_message_chat_to_app.text = message
    }
}




//        message = intent?.getStringExtra("MESSAGE_CONTENT")
//        messageType = intent.getIntExtra("MESSAGE_TYPE", 0)
//
//
//        img_back_messageDetail.setOnClickListener {
//            finish()
//        }
//
//        adapter.add(ChatFromItem("Hi, Welcome to free bee Send some love to friends and family today and wish them a happy friday"))
//        adapter.add(ChatToItemSMS("Thank you for warm welcome"))
//        adapter.add(ChatToItemApp("Hello, Where are you?"))
//
//        when (messageType){
//            0 -> {
//                adapter.add(ChatToItemSMS(message!!))
//            }
//            1 -> {
//                adapter.add(ChatToItemApp(message!!))
//            }
//
//        }
//
//        recycler_chat_messageDetail.adapter = adapter