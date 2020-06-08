package app.pldt.appvno.ui.notification

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
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_deactivate_account.*
import kotlinx.android.synthetic.main.fragment_reset_password_last.*


class NotificationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbarTitle.text = "Notifications"
        toolbarClose.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        toolbarNotif.visibility = View.GONE
        setupButtons()
    }


    private fun setupButtons() {}

    companion object {
        fun newInstance() = NotificationFragment()
    }
}
