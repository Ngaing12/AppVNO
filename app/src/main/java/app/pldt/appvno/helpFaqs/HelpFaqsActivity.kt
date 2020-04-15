package app.pldt.appvno.helpFaqs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.pldt.appvno.R
import kotlinx.android.synthetic.main.app_bar_with_back.*

class HelpFaqsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_faqs)
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbarBack.setOnClickListener { onBackPressed() }
        toolbarTitle.text = "Support"
    }
}
