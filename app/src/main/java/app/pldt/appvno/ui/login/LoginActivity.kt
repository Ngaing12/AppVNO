package app.pldt.appvno.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import app.pldt.appvno.R
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.model.TempUser
import app.pldt.appvno.ui.loginRegister.LoginBottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.bottom_sheet_login.*
import kotlinx.android.synthetic.main.recycler_contact_item.view.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)

        login_edt_countryCode.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }

        loginBSheet_img_close.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }

        bottomSheetBehavior.addBottomSheetCallback(  object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED-> {
                        login_img_blocker.isVisible(true)
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        login_img_blocker.isVisible(true)
                    }
                    BottomSheetBehavior.STATE_SETTLING ->{
                        login_img_blocker.isVisible(true)
                    }
                    else -> {
                        login_img_blocker.isVisible(false)
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



class MessageContact (val user: TempUser): Item<GroupieViewHolder>(){
    override fun getLayout() = R.layout.recycler_contact_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.recyclerContact_tv_name.text = user.email
        viewHolder.itemView.recyclerContact_tv_latest.text = user.number
    }
}

