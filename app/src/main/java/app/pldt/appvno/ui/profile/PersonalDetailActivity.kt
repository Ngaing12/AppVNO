package app.pldt.appvno.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.pldt.appvno.R
import kotlinx.android.synthetic.main.activity_personal_detail.*

class PersonalDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_detail)
        setMonths()
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
