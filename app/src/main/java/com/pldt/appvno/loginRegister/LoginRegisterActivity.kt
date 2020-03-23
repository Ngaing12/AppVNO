package com.pldt.appvno.loginRegister

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.pldt.appvno.R
import com.pldt.appvno.extensions.addCountryCode
import com.pldt.appvno.extensions.formatNumber
import com.pldt.appvno.home.HomeActivity
import com.pldt.appvno.otp.OtpConfirmationActivity
import com.pldt.appvno.privacyPolicy.PrivacyPolicyActivity
import kotlinx.android.synthetic.main.activity_login_register.*
import kotlinx.android.synthetic.main.dialog_verify_number.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class LoginRegisterActivity : AppCompatActivity(),LoginFragment.OnLoginInteractionListener, RegisterFragment.OnRegisterInteractionListener {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        onAttachFragment()
        attachListener()
    }

    private fun attachListener() {
        //         Create underline
        val udata = resources.getString(R.string.temp_privacy_policy)
        val content = SpannableString(udata)
        content.setSpan(UnderlineSpan(), 0, udata.length, 0)
        tv_view_privacy_loginRegister.text = content

        tv_view_privacy_loginRegister.setOnClickListener {
            startActivity<PrivacyPolicyActivity>()
        }

        img_close_login.setOnClickListener {
            container_fingerprint_login.visibility = View.GONE
        }

        img_fingerprint_login.setOnClickListener {
            // TODO - add Fingerprint auth
            // TODO - go to home screen
        }
    }

    private fun onAttachFragment() {
        replaceFragment(LoginFragment.newInstance())
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_loginRegister, fragment)
            addToBackStack(null)
            commit()
        }
    }


    // region Login Interface
    override fun onLoginClickSignUp() {
        replaceFragment(RegisterFragment.newInstance())
    }

    override fun onLoginClickLogin() {
        startActivity(intentFor<HomeActivity>().clearTask())
    }

    override fun onLoginClickFingerprint() {
        container_fingerprint_login.visibility = View.VISIBLE
    }
    // endregion

    // region Register Interface
    override fun onRegisterClickRegister(number: String) {
       // TODO - do register
        openVerifyDialog(number)
    }

    override fun onRegisterClickSignin() {
        replaceFragment(LoginFragment.newInstance())
    }
    // endregion


    fun openVerifyDialog(number: String){
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
            // TODO - open new activity
            startActivity<OtpConfirmationActivity>("CONFIRM_NUMBER" to formatedNumber)
            confirmDialog.dismiss()
        }

        // Make the dialog background transparent
        confirmDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        confirmDialog.show()
    }

}
