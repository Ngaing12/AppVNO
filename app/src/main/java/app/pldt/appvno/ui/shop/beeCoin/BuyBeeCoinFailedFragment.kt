package app.pldt.appvno.ui.shop.beeCoin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputFilter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.pldt.appvno.R
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import app.pldt.appvno.ui.shop.ShopFragment
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_buy_bee_coin_failed.*


class BuyBeeCoinFailedFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_buy_bee_coin_failed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbarTitle.text = "Buy Bee Coins"
        toolbarClose.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        setupButtons()
    }

    private fun setupButtons() {
        btn_try.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragment(MyBeeCoinFragment.newInstance())
        }
        tv_choose.setOnClickListener {
            // Go to my bee coin
            fragmentManager?.popBackStack()
        }
    }

    companion object {
        fun newInstance() = BuyBeeCoinFailedFragment()
    }
}
