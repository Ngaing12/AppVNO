package app.pldt.appvno.ui.password.resetPassword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.pldt.appvno.R
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import app.pldt.appvno.ui.notification.NotificationFragment
import app.pldt.appvno.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_deactivate_account.*
import kotlinx.android.synthetic.main.fragment_reset_password_create.*
import kotlinx.android.synthetic.main.fragment_reset_password_last.*
import kotlinx.android.synthetic.main.fragment_reset_password_last.edt_password


class ResetPasswordCreateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reset_password_create, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbarTitle.text = "Password Reset"
        toolbarClose.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        toolbarNotif.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(NotificationFragment.newInstance(), 1)
        }
        setupButtons()
        setupEditTexts()
    }


    private fun setupButtons() {
        btn_continue.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.isChangePass = true
            a.setCurrentFragment(ProfileFragment.newInstance())
        }
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

    private fun isLegalPassword(pass: String): Boolean {
        if (!pass.matches(".*[A-Z].*".toRegex())) return false
        if (!pass.matches(".*[a-z].*".toRegex())) return false
        if (!pass.matches(".*\\d.*".toRegex())) return false
        //   if (!pass.matches(".*[~!.......].*".toRegex())) return false
        return  true
    }


    companion object {
        fun newInstance() = ResetPasswordCreateFragment()
    }
}
