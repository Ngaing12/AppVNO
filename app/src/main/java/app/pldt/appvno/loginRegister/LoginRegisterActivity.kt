package app.pldt.appvno.loginRegister

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.afollestad.materialdialogs.MaterialDialog
import app.pldt.appvno.R
import app.pldt.appvno.extensions.addCountryCode
import app.pldt.appvno.extensions.formatNumber
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.home.HomeActivity
import app.pldt.appvno.otp.OtpConfirmationActivity
import kotlinx.android.synthetic.main.dialog_verify_number.*
import kotlinx.android.synthetic.main.include_login.*
import kotlinx.android.synthetic.main.include_login_fingerprint.*
import kotlinx.android.synthetic.main.include_register.*
import kotlinx.android.synthetic.main.include_register.register_btn_register
import kotlinx.android.synthetic.main.include_register.register_edt_phone_number
import kotlinx.android.synthetic.main.include_register.register_tv_signin
import kotlinx.android.synthetic.main.include_register.register_spinner_location
import org.jetbrains.anko.*

class LoginRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        attachListener()
        setupSpinner()
    }

    private fun attachListener() {

        login_img_fingerprint.setOnClickListener {
            loginFingerprint_group.isVisible(true)
        }

        loginFingerprint_img_close.setOnClickListener {
            loginFingerprint_group.isVisible(false)
        }

        login_tv_register.setOnClickListener {
            // TODO - Clear edit Text
            login_group.isVisible(false)
            register_group.isVisible(true)

        }

        register_tv_signin.setOnClickListener {
            // TODO - Clear edit Text
            register_group.isVisible(false)
            login_group.isVisible(true)
        }

        loginFingerprint_img_fingerprint.setOnClickListener {
            // TODO - add Fingerprint auth
            startActivity(intentFor<HomeActivity>().newTask().clearTask())
        }

        login_btn_login.setOnClickListener {
            startActivity(intentFor<HomeActivity>().newTask().clearTask())
        }


        register_btn_register.setOnClickListener {
            // TODO - Check country and api call to check number
            val number = register_edt_phone_number.text.toString()

            if (number.isEmpty() ||
                number.length < 10){
                toast("Invalid number")
                return@setOnClickListener
            }
            openVerifyDialog(number)
        }
    }


    private fun openVerifyDialog(number: String){
        val confirmDialog = MaterialDialog.Builder(this)
            .customView(R.layout.dialog_verify_number, false)
            .cancelable(false)
            .build()

        val formatedNumber = number.formatNumber().addCountryCode("63")
        // Set the Origin and Destination data
        confirmDialog.tv_number_d_verify.text = formatedNumber

        // Setup actions of buttons
        confirmDialog.btn_cancel_d_verify.setOnClickListener {
            confirmDialog.dismiss()
        }
        confirmDialog.btn_continue_d_verify.setOnClickListener {
            startActivity<OtpConfirmationActivity>("CONFIRM_NUMBER" to formatedNumber)
            confirmDialog.dismiss()
        }

        // Make the dialog background transparent
        confirmDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        confirmDialog.show()
    }


    private fun setupSpinner() {
        // TODO - add data
        register_spinner_location.setItems("Philippines")
        register_spinner_location.setOnItemSelectedListener { view, position, id, item ->
            Log.d("Sample", item.toString())
//            Snackbar.make(
//                view,
//                "Clicked $item",
//                Snackbar.LENGTH_LONG
//            ).show()
        }
    }
}
