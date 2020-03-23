package com.pldt.appvno.termsCondition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pldt.appvno.R
import com.pldt.appvno.home.HomeActivity
import kotlinx.android.synthetic.main.activity_terms_condition.*
import org.jetbrains.anko.startActivity

class TermsConditionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_condition)

        attachListener()
    }

    private fun attachListener() {
        btn_agree_termsCondition.setOnClickListener {
            startActivity<HomeActivity>()
        }

        img_back_termsCondition.setOnClickListener {
            finish()
        }
    }
}
