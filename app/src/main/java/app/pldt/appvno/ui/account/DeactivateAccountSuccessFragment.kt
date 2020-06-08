package app.pldt.appvno.ui.account

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.pldt.appvno.R
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_deactivate_account.*


class DeactivateAccountSuccessFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_deactivate_account_success, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbarTitle.gravity = Gravity.CENTER
        toolbarTitle.text = "Deactivate Account"
        toolbarClose.visibility = View.GONE
        toolbarNotif.visibility = View.GONE
        setupButtons()
    }

    private fun setupButtons() {

    }

    companion object {
        fun newInstance() = DeactivateAccountSuccessFragment()
    }
}
