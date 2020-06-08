package app.pldt.appvno.ui.profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import app.pldt.appvno.R
import app.pldt.appvno.ui.account.DeactivateAccountFragment
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import app.pldt.appvno.ui.notification.NotificationFragment
import app.pldt.appvno.ui.password.resetPassword.ResetPasswordLastFragment
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.dialog_password_updated.view.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    var passChangeDialog : MaterialDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        toolbarTitle.text = "Profile"
        toolbarClose.visibility = View.GONE
        toolbarClose.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        toolbarNotif.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(NotificationFragment.newInstance(), 1)
        }
        setupButtons()
        setupDialog()

        // TODO - Temp
        val a = activity as FreebeeHomeActivity
        if (a.isChangePass) {
            // Show Modal
            passChangeDialog?.show()
            a.isChangePass = false
        }
    }


    private fun setupButtons() {
        card_5.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(DeactivateAccountFragment.newInstance())
        }
        tv_changePassword.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(ResetPasswordLastFragment.newInstance())
        }
    }

    private fun setupDialog() {
        context?.let {
            passChangeDialog = MaterialDialog.Builder(it)
                .customView(R.layout.dialog_password_updated, false)
                .cancelable(false)
                .build()

            passChangeDialog?.view?.profile_img_close?.setOnClickListener {
                // Goto Next Activity
                passChangeDialog?.dismiss()

            }
            passChangeDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            passChangeDialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT  )

        }
    }


    companion object {
        fun newInstance() = ProfileFragment()
    }
}
