package app.pldt.appvno.ui.securityQuestion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import app.pldt.appvno.R
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import app.pldt.appvno.ui.login.LoginActivity
import app.pldt.appvno.ui.profile.SpinnerDefaultAdapter
import kotlinx.android.synthetic.main.activity_security_question.*
import kotlinx.android.synthetic.main.app_bar_pre_login.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity

class SecurityQuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_question)

        toolbarTitle.text = "Security Question"
        toolbarClose.setOnClickListener {
            startActivity(intentFor<FreebeeHomeActivity>().newTask().clearTask())
        }

        setupDummyData()
        setupButtons()
        setupEditTexts()
    }

    private fun setupEditTexts() {
        edt_answer.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn_continue.isEnabled = s?.count() ?: 0 > 0
            }

        })
    }

    private fun setupButtons() {
        btn_continue.setOnClickListener {
            startActivity(intentFor<SecurityQuestionSuccessActivity>().newTask().clearTask())
        }
    }


    private fun setupDummyData() {
        val dummyData = listOf("Select your Security Question", "Something", "Anything")
        val spinnerArrayAdapter = SpinnerDefaultAdapter(
            this,
            dummyData
        )
        spinner_question.adapter = spinnerArrayAdapter
        spinner_question.setSelection(0)

        spinner_question.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
              //  spinner_question.setTextC==
                btn_continue.isEnabled = position > 0
            }
        }

        val s =  arrayOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
            "Item 6",
            "Other"
        )

        val adapter = ArrayAdapter<String>(
            this,
            R.layout.material_spinner_freebee,
            s
        )
        autoComplete.setAdapter(adapter)

    }

}
