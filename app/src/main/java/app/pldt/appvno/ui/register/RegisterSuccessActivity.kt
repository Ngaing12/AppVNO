package app.pldt.appvno.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.pldt.appvno.R
import app.pldt.appvno.extensions.isVisible
import kotlinx.android.synthetic.main.app_bar_pre_login.*

class RegisterSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_success)

        toolbarClose.visibility = View.INVISIBLE
    }
}
