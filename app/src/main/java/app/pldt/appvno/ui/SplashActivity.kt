package app.pldt.appvno.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.pldt.appvno.ui.getStarted.GetStartedActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity<GetStartedActivity>()
        finish()
    }
}
