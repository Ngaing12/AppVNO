package app.pldt.appvno.termsCondition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.pldt.appvno.R
import app.pldt.appvno.home.HomeActivity
import kotlinx.android.synthetic.main.activity_terms_condition.*
import kotlinx.android.synthetic.main.app_bar_with_back.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class TermsConditionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_condition)

        setupToolbar()
        attachListener()
    }

    private fun setupToolbar() {
        toolbarTitle.text = resources.getText(R.string.terms_and_condition)
        toolbarBack.setOnClickListener {finish() }
    }

    private fun attachListener() {
        termsCondition_btn_agree.setOnClickListener {
            startActivity(intentFor<HomeActivity>().newTask().clearTask())
        }
    }
}
