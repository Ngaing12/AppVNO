package app.pldt.appvno.ui.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.pldt.appvno.R
import kotlinx.android.synthetic.main.app_bar_pre_login.*

class AccountLockedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_locked)

        toolbarTitle.text = "Forgot Password"
        toolbarClose.visibility = View.INVISIBLE

    }
}
