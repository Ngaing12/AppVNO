package com.pldt.appvno.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pldt.appvno.R
import com.pldt.appvno.message.NewMessage
import kotlinx.android.synthetic.main.fragment_message.*
import org.jetbrains.anko.startActivity

class MessageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachListener()
    }

    private fun attachListener() {
        img_message_messageFragment.setOnClickListener {
            activity?.startActivity<NewMessage>()
        }
    }

    companion object {
        fun newInstance() = MessageFragment()
    }
}
