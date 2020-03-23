package com.pldt.appvno.getStarted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import com.pldt.appvno.R
import com.pldt.appvno.loginRegister.LoginRegisterActivity
import com.pldt.appvno.privacyPolicy.PrivacyPolicyActivity
import kotlinx.android.synthetic.main.activity_get_started.*
import org.jetbrains.anko.startActivity

class GetStartedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        attachListener()

    }

    private fun attachListener() {
        //         Create underline
        val udata = resources.getString(R.string.temp_privacy_policy)
        val content = SpannableString(udata)
        content.setSpan(UnderlineSpan(), 0, udata.length, 0)
        tv_view_privacy_getStarted.text = content

        tv_view_privacy_getStarted.setOnClickListener {
            startActivity<PrivacyPolicyActivity>()
        }

        btn_start_getStarted.setOnClickListener{
            startActivity<LoginRegisterActivity>()
            finish()
        }
    }
}
