package app.pldt.appvno.ui.securityQuestion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.pldt.appvno.R
import app.pldt.appvno.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_security_question_success.*
import kotlinx.android.synthetic.main.app_bar_pre_login.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity

class SecurityQuestionSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_question_success)

        toolbarTitle.text = "Security Question"
        toolbarClose.visibility = View.INVISIBLE

        setupButtons()
    }

    private fun setupButtons() {
        btn_continue.setOnClickListener {
            // TODO - goto home
            startActivity(intentFor<LoginActivity>().newTask().clearTask())
        }
    }
}
