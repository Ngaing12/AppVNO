package app.pldt.appvno.ui.password.forgotPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import app.pldt.appvno.R
import kotlinx.android.synthetic.main.activity_forgot_pass_account_verification.*
import kotlinx.android.synthetic.main.app_bar_pre_login.*

class ForgotPassAccountVerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass_account_verification)

        toolbarTitle.text = "Forgot Password"

        setupButtons()
        setupEditTexts()
    }

    private fun setupEditTexts() {
        edt_answer.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn_submit.isEnabled = s?.count() ?: 0 >  0
            }

        })
    }

    private fun setupButtons() {

    }
}
