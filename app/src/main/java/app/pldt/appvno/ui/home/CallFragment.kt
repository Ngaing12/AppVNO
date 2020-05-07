package app.pldt.appvno.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.pldt.appvno.R

/**
 * A simple [Fragment] subclass.
 */
class CallFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        attachListener()
    }

    private fun attachListener() {
    }


    companion object {
        fun newInstance() = CallFragment()
    }

}
