package app.pldt.appvno.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import app.pldt.appvno.R
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.model.Country
import app.pldt.appvno.model.TempUser
import app.pldt.appvno.ui.login.CountryCodeItem
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.bottom_sheet_login.*

class RegisterActivity : AppCompatActivity() {

    val adapter = GroupAdapter<GroupieViewHolder>()
    lateinit var bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupDummyData()
        setupBottomSheet()
        setupButtons()
    }

    private fun setupButtons() {
        btn_next.setOnClickListener {
            if (edt_country.text.toString() == ""){
                edt_country.setError("This field is required!", null)
            }
            if (edt_mobileNumber.text.toString() == ""){
                edt_mobileNumber.setError("This field is required!", null)
            }
        }

        edt_countryCode.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
        edt_country.setOnClickListener{
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
