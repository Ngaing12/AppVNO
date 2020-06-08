package app.pldt.appvno.ui.shop.beeCoin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputFilter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import app.pldt.appvno.R
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import app.pldt.appvno.ui.notification.NotificationFragment
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.dialog_confirm_purchase.view.*
import kotlinx.android.synthetic.main.dialog_enter_promo.*
import kotlinx.android.synthetic.main.fragment_buy_bee_coin_confirm.*
import kotlinx.android.synthetic.main.fragment_shop.*

class BuyBeeCoinCofirmFragment : Fragment() {

    var purchaseDialog : MaterialDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_buy_bee_coin_confirm, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        toolbarTitle.text = "Buy Bee Coins"
        toolbarClose.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        toolbarNotif.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(NotificationFragment.newInstance(), 1)
        }
        setupButtons()
        setupDialog()
    }


    private fun setupButtons() {
        btn_cancel.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        btn_buy.setOnClickListener {
            // Pop Dialog
            purchaseDialog?.show()
            purchaseDialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT  )
        }
    }


    private fun setupDialog() {
        if (context == null) return
        purchaseDialog = this.context?.let {
            MaterialDialog.Builder(it)
                .customView(R.layout.dialog_confirm_purchase, true)
                .cancelable(false)
                .build()
        }

        purchaseDialog?.view?.payment_btn_okay?.setOnClickListener {
            // Goto Next Activity
            purchaseDialog?.dismiss()
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragment(BuyBeeCoinSuccessFragment.newInstance())
        }
        purchaseDialog?.view?.payment_btn_no?.setOnClickListener {
            purchaseDialog?.dismiss()
        }
        purchaseDialog?.edt_promo_promo?.filters = arrayOf<InputFilter>(InputFilter.AllCaps())
        purchaseDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        purchaseDialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT  )

    }

    companion object {
        fun newInstance() = BuyBeeCoinCofirmFragment()
    }
}
