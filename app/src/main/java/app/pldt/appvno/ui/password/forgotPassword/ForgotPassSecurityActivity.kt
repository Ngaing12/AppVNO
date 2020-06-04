package app.pldt.appvno.ui.password.forgotPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import app.pldt.appvno.R
import app.pldt.appvno.ui.profile.SpinnerDefaultAdapter
import kotlinx.android.synthetic.main.activity_forgot_pass_security.*
import kotlinx.android.synthetic.main.app_bar_pre_login.*

class ForgotPassSecurityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass_security)

        toolbarTitle.text = "Forgot Password"

        setupButtons()
        setupDummyData()
    }


    private fun setupDummyData() {
        val dummyData = listOf("Select your Security Question", "Something", "Anything")
        val spinnerArrayAdapter = SpinnerDefaultAdapter(
            this,
            dummyData
        )
        spinner_question.adapter = spinnerArrayAdapter
        spinner_question.setSelection(0)

        spinner_question.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                btn_continue.isEnabled = position > 0
            }
        }
    }


    private fun setupButtons() {

    }
}
