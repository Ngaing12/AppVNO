package app.pldt.appvno.privacyPolicy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.pldt.appvno.R
import kotlinx.android.synthetic.main.activity_privacy_policy.*
import kotlinx.android.synthetic.main.app_bar_with_back.*

class PrivacyPolicyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        setupToolbar()
    }

    private fun setupToolbar() {
        toolbarTitle.text = resources.getText(R.string.temp_privacy_policy)

        toolbarBack.setOnClickListener {
            finish()
        }
    }
}
