package com.pldt.appvno.privacyPolicy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pldt.appvno.R
import kotlinx.android.synthetic.main.activity_privacy_policy.*

class PrivacyPolicyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        attachListner()
    }

    private fun attachListner() {
        img_back_privacyPolicy.setOnClickListener {
            finish()
        }
    }
}
