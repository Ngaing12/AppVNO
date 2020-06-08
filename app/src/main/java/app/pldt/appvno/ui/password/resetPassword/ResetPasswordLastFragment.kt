package app.pldt.appvno.ui.password.resetPassword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.pldt.appvno.R
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import app.pldt.appvno.ui.notification.NotificationFragment
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_deactivate_account.*
import kotlinx.android.synthetic.main.fragment_reset_password_last.*


class ResetPasswordLastFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reset_password_last, container, false)
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

    private fun setupEditTexts() {
        edt_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn_next.isEnabled = s?.count() ?: 0 > 0
            }
        })
    }

    private fun setupButtons() {
        btn_next.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(ResetPasswordCreateFragment.newInstance())
        }
    }

    companion object {
        fun newInstance() = ResetPasswordLastFragment()
    }
}
