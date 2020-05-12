package app.pldt.appvno.ui.getStarted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.pldt.appvno.R
import app.pldt.appvno.ui.loginRegister.LoginRegisterActivity
import app.pldt.appvno.ui.privacyPolicy.PrivacyPolicyActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_get_started.*
import org.jetbrains.anko.startActivity

class GetStartedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)
        // TODO - (If needed only show for the first time)
        attachListener()
    }

    fun attachListener() {
        getStarted_tv_view_privacy.setOnClickListener {
            startActivity<PrivacyPolicyActivity>()
        }
        getStarted_btn_getStarted.setOnClickListener{
            startActivity<LoginRegisterActivity>()
            finish()
        }
    }
}
