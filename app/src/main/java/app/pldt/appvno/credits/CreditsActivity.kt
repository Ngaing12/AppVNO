package app.pldt.appvno.credits

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import app.pldt.appvno.R
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_credits.*
import kotlinx.android.synthetic.main.app_bar_with_back.*
import kotlinx.android.synthetic.main.dialog_confirm_promo.*
import kotlinx.android.synthetic.main.dialog_enter_promo.*

class CreditsActivity : AppCompatActivity() {

    private lateinit var promoDialog: MaterialDialog
    private lateinit var confirmationDialog: MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        setupToolbar()
        setupDialog()
        attachListener()
    }

    private fun attachListener() {
        tv_enterPromo_credit.setOnClickListener {
            promoDialog.show()
        }
    }

    private fun setupToolbar() {
        toolbarBack.setOnClickListener { onBackPressed() }
        toolbarTitle.text = "Credits"
    }

    private fun setupDialog(){
        confirmationDialog = MaterialDialog.Builder(this)
            .customView(R.layout.dialog_confirm_promo, false)
            .cancelable(false)
            .build()

        confirmationDialog.btn_continue_confirm.setOnClickListener { confirmationDialog.dismiss() }
        confirmationDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        promoDialog = MaterialDialog.Builder(this)
            .customView(R.layout.dialog_enter_promo, false)
            .cancelable(false)
            .build()

        promoDialog.btn_continue_promo.setOnClickListener {
            promoDialog.edt_promo_promo.text.clear()
            promoDialog.dismiss()
            confirmationDialog.show()

        }
        promoDialog.btn_cancel_promo.setOnClickListener {
            promoDialog.edt_promo_promo.text.clear()
            promoDialog.dismiss()
        }
        promoDialog.edt_promo_promo.filters = arrayOf<InputFilter>(InputFilter.AllCaps())
        promoDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}
