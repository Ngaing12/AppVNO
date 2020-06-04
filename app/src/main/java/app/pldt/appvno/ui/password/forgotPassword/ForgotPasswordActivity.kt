package app.pldt.appvno.ui.password.forgotPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import app.pldt.appvno.R
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.model.Country
import app.pldt.appvno.ui.login.CountryCodeItem
import app.pldt.appvno.ui.otp.ForgotPassOtpVerificationActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.app_bar_pre_login.*
import kotlinx.android.synthetic.main.bottom_sheet_login.*
import kotlinx.android.synthetic.main.dialog_touch_id_use.*
import org.jetbrains.anko.startActivity

class ForgotPasswordActivity : AppCompatActivity() {


    val adapter = GroupAdapter<GroupieViewHolder>()
    lateinit var bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        toolbarTitle.text = "Forgot Password"
        toolbarClose.setOnClickListener {
            finish()
        }

        setupBottomSheet()
        setupDummyData()
        setupButtons()
        setupEditTexts()
    }

    private fun setupEditTexts() {
        edt_mobileNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn_continue.isEnabled = s?.count() ?: 0 > 0
            }
        })
    }

    private fun setupButtons() {
        touchId_btn_yes.setOnClickListener {
            img_blocker.isVisible(false)
            touchUse_dialog.isVisible(false)
            startActivity<ForgotPassLastPassActivity>()
        }
        touchId_btn_no.setOnClickListener {
            img_blocker.isVisible(false)
            touchUse_dialog.isVisible(false)
            startActivity<ForgotPassOtpVerificationActivity>()
        }
        touchId_img_close.setOnClickListener{
            img_blocker.isVisible(false)
            touchUse_dialog.isVisible(false)
        }

        btn_continue.setOnClickListener {
            img_blocker.isVisible(true)
            touchUse_dialog.isVisible(true)
        }

        loginBSheet_img_close.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
        edt_countryCode.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
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
