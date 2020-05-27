package app.pldt.appvno.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.pldt.appvno.R
import app.pldt.appvno.ui.contactUs.ContactUsActivity
import app.pldt.appvno.ui.credits.CreditsActivity
import app.pldt.appvno.ui.helpFaqs.HelpFaqsActivity
import app.pldt.appvno.ui.loginRegister.LoginRegisterActivity
import app.pldt.appvno.ui.profile.ProfileActivity
import com.google.firebase.auth.FirebaseAuth
import com.sysnetph.sysnetsdk.Sysnet
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

        attachListener()
    }

    private fun attachListener() {


        tv_contact_otherFragment.setOnClickListener {
            activity?.startActivity<ContactUsActivity>()
        }
        tv_help_otherFragment.setOnClickListener {
            activity?.startActivity<HelpFaqsActivity>()
        }

        tv_credit_otherFragment.setOnClickListener {
            activity?.startActivity<CreditsActivity>()
        }

        tv_profile_otherFragment.setOnClickListener {
            activity?.startActivity<ProfileActivity>()
        }
        tv_logout_otherFragment.setOnClickListener {
            Sysnet.getInstance().unregister()
            FirebaseAuth.getInstance().signOut()
            activity?.startActivity(activity?.intentFor<LoginRegisterActivity>()?.newTask()?.clearTask())
        }
    }

    companion object {
        fun newInstance() = OtherFragment()
    }

}
