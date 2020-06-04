package app.pldt.appvno.ui.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.pldt.appvno.R
import app.pldt.appvno.ui.login.LoginActivity
import app.pldt.appvno.ui.password.forgotPassword.ForgotPassCreateActivity
import kotlinx.android.synthetic.main.activity_account_locked.*
import kotlinx.android.synthetic.main.app_bar_pre_login.*
import org.jetbrains.anko.startActivity

class AccountLockedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_locked)

        toolbarTitle.text = "Forgot Password"
        toolbarClose.visibility = View.INVISIBLE

        setupButtons()
    }

    private fun setupButtons() {
        btn_support.setOnClickListener {
            startActivity<ForgotPassCreateActivity>()
        }
        tv_back.setOnClickListener {
            startActivity<LoginActivity>()
        }
    }
}
