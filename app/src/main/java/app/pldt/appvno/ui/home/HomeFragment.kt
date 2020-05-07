package app.pldt.appvno.ui.home


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import app.pldt.appvno.AppVNOApplication

import app.pldt.appvno.R
import app.pldt.appvno.location.LocationRequestManager
import app.pldt.appvno.ui.models.LocationDetails
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private var listener: OnHomeInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHomeInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        attachListener()
        subscribeUi()
    }

    override fun onResume() {
        super.onResume()
        subscribeUi()
    }

    private fun subscribeUi() {
        AppVNOApplication.getInstance().getPoints().observe(viewLifecycleOwner, Observer<Int> { points ->
            points?.let { it ->
                tv_money_fragmentHome.text = it.toString()
            }
        })

        LocationRequestManager.getCurrentLocationDetail().observe(viewLifecycleOwner, Observer<LocationDetails> { details ->
           if (details == null){
               tv_location_fragmentHome.text = "Unknown Location"
           }
           else {
               tv_location_fragmentHome.text =
                   "${details.streetNo} ${details.street} ${details.city}, ${details.countryCode} \n ${details.postalCode}, ${details.countryCode}"
           }
        })

    }

    private fun attachListener() {
        img_call_homeFragment.setOnClickListener {
            listener?.onClickCall()
        }
    }

    interface OnHomeInteractionListener {
        fun onClickCall()
    }


    companion object {
        fun newInstance() = HomeFragment()
    }
}
