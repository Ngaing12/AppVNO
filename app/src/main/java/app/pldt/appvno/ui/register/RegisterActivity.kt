package app.pldt.appvno.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import app.pldt.appvno.R
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.model.TempUser
import app.pldt.appvno.ui.login.MessageContact
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.bottom_sheet_login.*

class RegisterActivity : AppCompatActivity() {

    val adapter = GroupAdapter<GroupieViewHolder>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        var bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)

        register_img_search.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }

        loginBSheet_img_close.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }

        bottomSheetBehavior.addBottomSheetCallback(  object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED-> {
                        register_img_blocker.isVisible(true)
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        register_img_blocker.isVisible(true)
                    }
                    BottomSheetBehavior.STATE_SETTLING ->{
                        register_img_blocker.isVisible(true)
                    }
                    else -> {
                        register_img_blocker.isVisible(false)
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Do something for slide offset
            }
        })
        val tempUser1 = TempUser(
            "9000000000",
            "sample@gmail.com",
            "123456",
            "YsoykGNdT9azgDYZGtrX1RFR6Pg1"
        )

        adapter.add(MessageContact(tempUser1))
        adapter.add(MessageContact(tempUser1))
        adapter.add(MessageContact(tempUser1))
        adapter.add(MessageContact(tempUser1))
        adapter.add(MessageContact(tempUser1))
        loginBSheet_rv_country.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter.setOnItemClickListener { item, _ ->
//            val row = item as MessageContact
//
//            activity?.startActivity<MessageDetailActivity>(CONTACT_INFO to row.user)
        }

        loginBSheet_rv_country.adapter = adapter
    }
}
