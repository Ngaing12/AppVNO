package com.pldt.appvno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pldt.appvno.getStarted.GetStartedActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)

         startActivity<GetStartedActivity>()
        finish()

    }
}
