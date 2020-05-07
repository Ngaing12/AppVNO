package app.pldt.appvno.ui.otp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import app.pldt.appvno.R
import app.pldt.appvno.ui.profile.InputProfileActivity
import kotlinx.android.synthetic.main.activity_otp_confirmation.*
import kotlinx.android.synthetic.main.app_bar_with_back.*
import org.jetbrains.anko.startActivity

class OtpConfirmationActivity : AppCompatActivity() {


    var otpCode = ""
    var inputBoxes = mutableListOf<EditText>()


    private var formattedNumber : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_confirmation)

        setupToolbar()
        setupEditText()
        attachListener()
    }

    private fun setupToolbar() {
        toolbarTitle.text =""
        toolbarBack.setOnClickListener { finish() }
    }

    private fun attachListener() {
        otpConfirmation_btn_submit.setOnClickListener{
            // TODO -  open new activity
            startActivity<InputProfileActivity>()
        }

        otpConfirmation_tv_resend.setOnClickListener {
            Toast.makeText(this, "Resending OTP", Toast.LENGTH_SHORT).show()
        }

        otpConfirmation_tv_wrong.setOnClickListener {
            finish()
        }
    }


    private fun setupEditText() {
        formattedNumber =  intent.getStringExtra("CONFIRM_NUMBER")
        otpConfirmation_tv_title.text  = "This is your number $formattedNumber"
//
//        inputBoxes.add(edt_number_1_otpConfirmation)
//        inputBoxes.add(edt_number_2_otpConfirmation)
//        inputBoxes.add(edt_number_3_otpConfirmation)
//        inputBoxes.add(edt_number_4_otpConfirmation)
//        inputBoxes.add(edt_number_5_otpConfirmation)
//        inputBoxes.add(edt_number_6_otpConfirmation)
//
//
//        inputBoxes[1].setOnFocusChangeListener { v, hasFocus ->
//            if (hasFocus) {
//                if (otpCode.isEmpty()){
//                    inputBoxes[0].requestFocus()
//                }
//            }
//        }
    }
}

