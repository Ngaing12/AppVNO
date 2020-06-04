package app.pldt.appvno.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import app.pldt.appvno.R
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.model.Country
import app.pldt.appvno.model.TempUser
import app.pldt.appvno.ui.loginRegister.LoginBottomSheetFragment
import app.pldt.appvno.ui.password.forgotPassword.ForgotPasswordActivity
import app.pldt.appvno.ui.register.RegisterActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.bottom_sheet_login.*
import kotlinx.android.synthetic.main.recycler_contact_item.view.*
import kotlinx.android.synthetic.main.recycler_country_code_item.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    val adapter = GroupAdapter<GroupieViewHolder>()
    lateinit var bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupBottomSheet()
        setupDummyData()
        setupButtons()

    }

    private fun setupButtons() {
        tv_signUp.setOnClickListener {
            startActivity<RegisterActivity>()
            finish()
        }
        tv_forgotPassword.setOnClickListener {
            startActivity<ForgotPasswordActivity>()
        }
        btn_login.setOnClickListener{
            if (edt_mobileNumber.text.toString() == "") {
                edt_mobileNumber.setError("This field is required!", null)
            }
        }

        edt_countryCode.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }

        loginBSheet_img_close.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
    }

    private fun setupBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)

        bottomSheetBehavior.addBottomSheetCallback(  object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED-> {
                        img_blocker.isVisible(true)
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        img_blocker.isVisible(true)
                    }
                    BottomSheetBehavior.STATE_SETTLING ->{
                        img_blocker.isVisible(true)
                    }
                    else -> {
                        img_blocker.isVisible(false)
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Do something for slide offset
            }
        })
    }

    private fun setupDummyData() {
        val country = Country(
            "+1",
            "Country of Something"
        )

        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.add(CountryCodeItem(country))
        adapter.setOnItemClickListener { _, _ ->
//            val row = item as MessageContact
//
//            activity?.startActivity<MessageDetailActivity>(CONTACT_INFO to row.user)
        }

        loginBSheet_rv_country.adapter = adapter
    }
}



class CountryCodeItem ( val country: Country): Item<GroupieViewHolder>(){
    override fun getLayout() = R.layout.recycler_country_code_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_code.text = country.code
        viewHolder.itemView.tv_country.text = country.name
    }
}

