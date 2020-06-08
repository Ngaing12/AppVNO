package app.pldt.appvno.ui.otp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.widget.addTextChangedListener
import app.pldt.appvno.R
import app.pldt.appvno.ui.password.createPassword.CreatePasswordActivity
import kotlinx.android.synthetic.main.activity_otp_verification.*
import kotlinx.android.synthetic.main.app_bar_pre_login.*
import org.jetbrains.anko.startActivity

class OtpVerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)


        toolbarTitle.text = "Sign Up"
        toolbarClose.setOnClickListener {
            finish()
        }

        setupPinview()
        setupButtons()
    }

    private fun setupButtons() {

        btn_submit.setOnClickListener {
            startActivity<CreatePasswordActivity>()
        }
    }

    private fun setupPinview() {
        pinview_otp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                btn_submit.isEnabled = s?.count() ?: 0 == 6
            }

        })
    }
}
