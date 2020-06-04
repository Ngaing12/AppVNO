package app.pldt.appvno.ui.otp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.widget.addTextChangedListener
import app.pldt.appvno.R
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.ui.password.forgotPassword.ForgotPassLastPassActivity
import kotlinx.android.synthetic.main.activity_forgot_otp_verification.*
import kotlinx.android.synthetic.main.activity_otp_verification.*
import kotlinx.android.synthetic.main.activity_otp_verification.pinview_otp
import kotlinx.android.synthetic.main.app_bar_pre_login.*
import org.jetbrains.anko.startActivity

class ForgotPassOtpVerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_otp_verification)


        toolbarTitle.text = "Forgot Password"
        toolbarClose.setOnClickListener {
            finish()
        }

        setupButtons()

        pinview_otp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                btn_continue.isEnabled = s?.count() ?: 0 == 6
                Log.d("Test", s.toString())
            }

        })
    }

    private fun setupButtons() {
        btn_continue.setOnClickListener {
           startActivity<ForgotPassLastPassActivity>()
        }
    }
}
