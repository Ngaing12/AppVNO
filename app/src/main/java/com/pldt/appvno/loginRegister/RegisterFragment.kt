package com.pldt.appvno.loginRegister

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pldt.appvno.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    private var listener: OnRegisterInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupSpinner()
        attachListener()
    }

    private fun attachListener() {
        btn_register_register.setOnClickListener{
            if (edt_phone_number_register.text.toString().isEmpty() ||
                    edt_phone_number_register.text.toString().length < 10){
                Toast.makeText(activity, "Invalid number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            listener?.onRegisterClickRegister(edt_phone_number_register.text.toString())
        }

        tv_signin_register.setOnClickListener {
            listener?.onRegisterClickSignin()
        }
    }


    private fun setupSpinner() {
        spinner_location_register.setItems("Philippines")
        spinner_location_register.setOnItemSelectedListener { view, position, id, item ->
            Log.d("Sample", item.toString())
//            Snackbar.make(
//                view,
//                "Clicked $item",
//                Snackbar.LENGTH_LONG
//            ).show()
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRegisterInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnRegisterInteractionListener {
        fun onRegisterClickRegister(number : String)
        fun onRegisterClickSignin()
    }

    companion object {
        fun newInstance() = RegisterFragment()
    }
}
