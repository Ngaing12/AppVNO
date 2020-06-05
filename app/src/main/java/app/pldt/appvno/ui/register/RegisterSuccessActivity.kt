package app.pldt.appvno.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.pldt.appvno.R
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import app.pldt.appvno.ui.login.LoginActivity
import app.pldt.appvno.ui.securityQuestion.SecurityQuestionActivity
import kotlinx.android.synthetic.main.activity_register_success.*
import kotlinx.android.synthetic.main.app_bar_pre_login.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity

class RegisterSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_success)

        toolbarClose.visibility = View.INVISIBLE

        setupButtons()
    }

    private fun setupButtons() {

        tv_skip.setOnClickListener {
            // TODO - Go to home
            startActivity(intentFor<FreebeeHomeActivity>().newTask().clearTask())
        }

        btn_set.setOnClickListener {
            startActivity<SecurityQuestionActivity>()
            finish()
        }
    }
}
