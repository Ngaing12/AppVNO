package com.pldt.appvno.loginRegister

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pldt.appvno.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private var listener: OnLoginInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        attachListner()
    }

    private fun attachListner() {
        img_fingerprint_login.setOnClickListener {
            listener?.onLoginClickFingerprint()
        }

        btn_login_login.setOnClickListener{
            listener?.onLoginClickLogin()
        }

        tv_register_login.setOnClickListener {
            listener?.onLoginClickSignUp()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoginInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnLoginInteractionListener {
        fun onLoginClickSignUp()
        fun onLoginClickLogin()
        fun onLoginClickFingerprint()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }

}
