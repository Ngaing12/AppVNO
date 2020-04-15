package app.pldt.appvno.call


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.pldt.appvno.R

import kotlinx.android.synthetic.main.fragment_keypad.*

class KeypadFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keypad, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        attachListener()
    }

    private fun attachListener() {
        img_back_phoneFragment.setOnClickListener {
            activity?.finish()
        }
    }

    companion object {

        fun newInstance() = KeypadFragment()
    }
}
