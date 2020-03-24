package com.pldt.appvno.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pldt.appvno.R
import kotlinx.android.synthetic.main.activity_new_message.*

class NewMessage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        attachListener()
    }

    private fun attachListener() {
        img_back_newMessage.setOnClickListener {
            finish()
        }
    }
}
