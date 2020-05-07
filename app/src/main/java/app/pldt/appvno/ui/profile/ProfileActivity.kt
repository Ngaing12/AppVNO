package app.pldt.appvno.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.pldt.appvno.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        attachListener()
    }

    private fun attachListener() {
        img_back_profile.setOnClickListener {
            finish()
        }
    }
}
