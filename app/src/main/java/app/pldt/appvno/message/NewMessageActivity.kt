package app.pldt.appvno.message

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import app.pldt.appvno.R
import app.pldt.appvno.extensions.Text
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.dialog_chat_sms.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class NewMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        attachListener()
    }

    private fun attachListener() {
        img_back_newMessage.setOnClickListener {
            finish()
        }

        img_send_newMessage.setOnClickListener {
           openSendDialog()
        }
    }

    fun openSendDialog(){

        val message = edt_content_newMessage.Text()

        if (message.isEmpty()){
            toast("No message")
            return
        }

        val confirmDialog = MaterialDialog.Builder(this)
            .customView(R.layout.dialog_chat_sms, false)
            .cancelable(true)
            .build()

        // Setup actions of buttons
        confirmDialog.btn_send_chat.setOnClickListener {
            startActivity<MessageDetailActivity>(
                "MESSAGE_CONTENT" to message,
                "MESSAGE_TYPE" to 1
                )
            confirmDialog.dismiss()
        }
        confirmDialog.btn_send_sms.setOnClickListener {
            // TODO - open new activity
            startActivity<MessageDetailActivity>(
                "MESSAGE_CONTENT" to message,
                "MESSAGE_TYPE" to 0
            )
            confirmDialog.dismiss()
        }

        // Make the dialog background transparent
        confirmDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        confirmDialog.show()
    }

}
