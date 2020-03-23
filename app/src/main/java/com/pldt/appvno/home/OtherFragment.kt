package com.pldt.appvno.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pldt.appvno.R
import com.pldt.appvno.loginRegister.LoginRegisterActivity
import com.pldt.appvno.profile.ProfileActivity
import kotlinx.android.synthetic.main.fragment_other.*
import org.jetbrains.anko.*

/**
 * A simple [Fragment] subclass.
 */
class OtherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        attachListner()
    }

    private fun attachListner() {

        tv_profile_otherFragment.setOnClickListener {
            activity?.startActivity<ProfileActivity>()
        }
        tv_logout_otherFragment.setOnClickListener {
            activity?.startActivity(activity?.intentFor<LoginRegisterActivity>()?.newTask()?.clearTask())
        }
    }

    companion object {
        fun newInstance() = OtherFragment()
    }

}
