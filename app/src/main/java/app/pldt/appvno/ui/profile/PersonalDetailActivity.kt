package app.pldt.appvno.ui.profile

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import app.pldt.appvno.R
import app.pldt.appvno.ui.register.RegisterSuccessActivity
import kotlinx.android.synthetic.main.activity_personal_detail.*
import kotlinx.android.synthetic.main.app_bar_pre_login.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity

class PersonalDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_detail)

        toolbarClose.setOnClickListener {
            finish()
        }

        setMonths()
        setupButtons()
    }

    private fun setupButtons() {
        btn_continue.setOnClickListener {
            startActivity(intentFor<RegisterSuccessActivity>().newTask().clearTask())
        }
    }

    private fun setMonths() {
        val months = resources.getStringArray(R.array.months)
        val spinnerArrayAdapter = SpinnerDefaultAdapter(
            this,
            months.toList()
        )
        spinner_month.adapter = spinnerArrayAdapter
        spinner_month.setSelection(0)

    }

}
