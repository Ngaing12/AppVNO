package app.pldt.appvno.ui.shop.beeCoin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import app.pldt.appvno.R
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.dialog_invalid_pin.view.*
import kotlinx.android.synthetic.main.dialog_successs_purchase.view.*
import kotlinx.android.synthetic.main.fragment_my_bee_coin.*

class MyBeeCoinFragment : Fragment() {

    var invalidDialog : MaterialDialog? = null
    var successDialog : MaterialDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_bee_coin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        toolbarTitle.text = "My Bee Coins"
        toolbarClose.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        setupButtons()
        setupDialog()
    }




    private fun setupButtons() {
        btn_buy.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(BuyBeeCoinFragment.newInstance())
        }
        tv_submit.setOnClickListener {
            tv_submit.isEnabled = false
            img_help.visibility = View.VISIBLE
            tv_needHelp.visibility = View.VISIBLE
        }

        tv_needHelp.setOnClickListener {
            // Open dialog
            invalidDialog?.show()
        }

        img_help.setOnClickListener {
            // Open Dialog
            invalidDialog?.show()
        }
    }



    private fun setupDialog() {
        context?.let {
            invalidDialog = MaterialDialog.Builder(it)
                    .customView(R.layout.dialog_invalid_pin, true)
                    .cancelable(false)
                    .build()

            invalidDialog?.view?.invalid_img_close?.setOnClickListener {
                // Goto Next Activity
                invalidDialog?.dismiss()
                tv_submit.isEnabled = true
                img_help.visibility = View.GONE
                tv_needHelp.visibility = View.GONE
            }
            invalidDialog?.view?.invalid_tv_needHelp?.setOnClickListener {
                invalidDialog?.dismiss()
                // Open New Dialog
                successDialog?.show()
            }
            invalidDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            invalidDialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT  )

            context?.let {
                successDialog = MaterialDialog.Builder(it)
                    .customView(R.layout.dialog_successs_purchase, true)
                    .cancelable(false)
                    .build()

                successDialog?.view?.success_img_close?.setOnClickListener {
                    // Goto Next Activity
                    successDialog?.dismiss()
                    tv_submit.isEnabled = true
                    img_help.visibility = View.GONE
                    tv_needHelp.visibility = View.GONE
                }
                successDialog?.view?.success_tv_buy?.setOnClickListener {
                    successDialog?.dismiss()
                    tv_submit.isEnabled = true
                    img_help.visibility = View.GONE
                    tv_needHelp.visibility = View.GONE

                    val a = activity as FreebeeHomeActivity
                    a.setCurrentFragmentWithBackStack(BuyBeeCoinFragment.newInstance())
                }
                successDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                successDialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT  )
            }
        }

    }


    companion object {
        fun newInstance() = MyBeeCoinFragment()
    }
}
