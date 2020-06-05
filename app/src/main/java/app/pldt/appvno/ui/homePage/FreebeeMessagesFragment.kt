package app.pldt.appvno.ui.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.pldt.appvno.R

class FreebeeMessagesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_freebee_messages, container, false)
    }

    companion object {
        fun newInstance() = FreebeeMessagesFragment()
    }
}
