package app.pldt.appvno.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import app.pldt.appvno.common.SessionManager
import app.pldt.appvno.ui.getStarted.OnBoardingActivity
import app.pldt.appvno.ui.login.LoginActivity
import app.pldt.appvno.ui.loginRegister.LoginRegisterActivity
import com.sysnetph.sysnetsdk.Sysnet

class SplashActivity : AppCompatActivity() {


    private lateinit var mHandler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mHandler = Handler()
    }

    override fun onStart() {
        super.onStart()

        // Check whether the Service is already running
        if (Sysnet.isReady()) {
            onServiceReady()
        } else {
            // If it's not, let's start it
            startService(Intent().setClass(this, Sysnet::class.java))
            // And wait for it to be ready, so we can safely use it afterwards
            ServiceWaitThread().start()
        }
    }

    private fun onServiceReady() {
        // Once the service is ready, we can move on in the application
        // We'll forward the intent action, type and extras so it can be handled
        // by the next activity if needed, it's not the launcher job to do that
        val intent =  Intent()

       if (!SessionManager.isShowOnBoarding()) {
           intent.setClass(this, OnBoardingActivity::class.java)
       }else {
           intent.setClass(this, LoginActivity::class.java)
       }

        if (getIntent() != null && getIntent().extras != null) {
            getIntent().extras?.let { intent.putExtras(it) }
        }
        intent.action = getIntent().action
        intent.type = getIntent().type
        startActivity(intent)
        finish()
    }

    // This thread will periodically check if the Service is ready, and then call onServiceReady
    inner class ServiceWaitThread : Thread() {
        override fun run() {
            while (!Sysnet.isReady()) {
                try {
                    Log.d("Test", "Waiting for sysnet.")
                    sleep(100)
                } catch (e : InterruptedException ) {
                    throw  RuntimeException("waiting thread sleep() has been interrupted")
                }
            }
            // As we're in a thread, we can't do UI stuff in it, must post a runnable in UI thread
            mHandler.post { onServiceReady() }
        }
    }
}
