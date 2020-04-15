package app.pldt.appvno.privacyPolicy

import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.pldt.appvno.R
import kotlinx.android.synthetic.main.fragment_privacy_footer.*
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class PrivacyFooterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacy_footer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachListener()
    }

    private fun attachListener() {
        privacyFooter_tv_view_privacy.setOnClickListener {
            activity?.startActivity<PrivacyPolicyActivity>()
        }
    }
}
