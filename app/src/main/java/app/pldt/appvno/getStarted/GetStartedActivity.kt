package app.pldt.appvno.getStarted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import app.pldt.appvno.R
import app.pldt.appvno.loginRegister.LoginRegisterActivity
import app.pldt.appvno.privacyPolicy.PrivacyPolicyActivity
import kotlinx.android.synthetic.main.activity_get_started.*
import org.jetbrains.anko.startActivity

class GetStartedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        attachListener()
    }

    private fun attachListener() {
        getStarted_tv_view_privacy.setOnClickListener {
            startActivity<PrivacyPolicyActivity>()
        }
        getStarted_btn_getStarted.setOnClickListener{
            startActivity<LoginRegisterActivity>()
            finish()
        }
    }
}
