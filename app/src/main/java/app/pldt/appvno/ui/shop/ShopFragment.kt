package app.pldt.appvno.ui.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.pldt.appvno.R
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import app.pldt.appvno.ui.shop.beeCoin.BuyBeeCoinFragment
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_shop.*
import org.jetbrains.anko.toast

class ShopFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbarClose.visibility =View.GONE
        toolbarTitle.text = "Shop"

        setupButtons()
    }

    private fun setupButtons() {
        view_sample.setOnClickListener {
            // Goto BeeShop
            val a = activity as FreebeeHomeActivity
            a.setCurrentFragmentWithBackStack(BuyBeeCoinFragment.newInstance())
        }
    }

    companion object {
        fun newInstance() = ShopFragment()
    }
}
