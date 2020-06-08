package app.pldt.appvno.ui.shop.beeCoin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.pldt.appvno.R
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_buy_bee_coin.*
import kotlinx.android.synthetic.main.fragment_buy_bee_coin_confirm.*
import kotlinx.android.synthetic.main.fragment_shop.*

class BuyBeeCoinFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_buy_bee_coin, container, false)
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
        btn_coin1.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(BuyBeeCoinCofirmFragment.newInstance())
        }

        btn_coin2.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(BuyBeeCoinCofirmFragment.newInstance())
        }
        btn_coin3.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(BuyBeeCoinCofirmFragment.newInstance())
        }

        btn_coin4.setOnClickListener {
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(BuyBeeCoinCofirmFragment.newInstance())
        }
    }

    companion object {
        fun newInstance() = BuyBeeCoinFragment()
    }
}
