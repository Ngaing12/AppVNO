package app.pldt.appvno.ui.password.createPassword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import app.pldt.appvno.R
import kotlinx.android.synthetic.main.activity_create_password.*

class CreatePasswordActivity : AppCompatActivity() {

    // TODO - change layout if need scroller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_password)

        setupEditTexts()
    }

    private fun setupEditTexts() {
        edt_password.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("Test", count.toString())
                if (s?.count() ?: 0  >= 8)
                    img_first_check.visibility = View.VISIBLE
                else
                    img_first_check.visibility = View.INVISIBLE

                if (isLegalPassword(s.toString()))
                    img_second_check.visibility = View.VISIBLE
                else
                    img_second_check.visibility = View.INVISIBLE
            }
        })

        edt_confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO -  Create Job

            }
        })
    }

    fun isLegalPassword(pass: String): Boolean {
        if (!pass.matches(".*[A-Z].*".toRegex())) return false
        if (!pass.matches(".*[a-z].*".toRegex())) return false
        if (!pass.matches(".*\\d.*".toRegex())) return false
     //   if (!pass.matches(".*[~!.......].*".toRegex())) return false
        return  true
    }
}
