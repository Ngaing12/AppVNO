package app.pldt.appvno.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.pldt.appvno.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_message_detail.*
import kotlinx.android.synthetic.main.recycler_chat_from_row.view.*
import kotlinx.android.synthetic.main.recycler_chat_to_row_app.view.*
import kotlinx.android.synthetic.main.recycler_chat_to_row_sms.*
import kotlinx.android.synthetic.main.recycler_chat_to_row_sms.view.*

class MessageDetailActivity : AppCompatActivity() {

    var message : String? = ""
    var messageType : Int? = 0


    val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_detail)


        message = intent?.getStringExtra("MESSAGE_CONTENT")
        messageType = intent.getIntExtra("MESSAGE_TYPE", 0)


        img_back_messageDetail.setOnClickListener {
            finish()
        }

        adapter.add(ChatFromItem("Hi, Welcome to free bee Send some love to friends and family today and wish them a happy friday"))
        adapter.add(ChatToItemSMS("Thank you for warm welcome"))
        adapter.add(ChatToItemApp("Hello, Where are you?"))

        when (messageType){
            0 -> {
                adapter.add(ChatToItemSMS(message!!))
            }
            1 -> {
                adapter.add(ChatToItemApp(message!!))
            }

        }

        recycler_chat_messageDetail.adapter = adapter
    }


}



class ChatFromItem (val message: String): Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.recycler_chat_from_row
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_message_from_to.text = message
    }
}


class ChatToItemSMS (val message: String): Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.recycler_chat_to_row_sms
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_message_chat_to_sms.text = message
    }

}


class ChatToItemApp (val message: String): Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.recycler_chat_to_row_app
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_message_chat_to_app.text = message
    }

}