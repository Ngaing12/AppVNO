package app.pldt.appvno.ui.password.forgotPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import app.pldt.appvno.R
import kotlinx.android.synthetic.main.activity_forgot_pass_last_pass.*
import kotlinx.android.synthetic.main.app_bar_pre_login.*

class ForgotPassLastPassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass_last_pass)

        toolbarTitle.text = "Forgot Password"

        setupButtons()
        setupEditTexts()
    }

    private fun setupEditTexts() {
        edt_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn_continue.isEnabled = s?.count() ?: 0 > 0
            }
        })
    }

    private fun setupButtons() {

    }
}
