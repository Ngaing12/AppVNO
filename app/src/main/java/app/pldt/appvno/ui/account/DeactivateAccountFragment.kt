package app.pldt.appvno.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.pldt.appvno.R
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import app.pldt.appvno.ui.notification.NotificationFragment
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_deactivate_account.*


class DeactivateAccountFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_deactivate_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbarTitle.text = "Deactivate Account"
        toolbarClose.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        toolbarNotif.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(NotificationFragment.newInstance(), 1)
        }
        setupButtons()
    }

    private fun setupButtons() {
        btn_deactivate.setOnClickListener{
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragment(DeactivateAccountSuccessFragment.newInstance())
        }
        btn_cancel.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    companion object {
        fun newInstance() = DeactivateAccountFragment()
    }
}
