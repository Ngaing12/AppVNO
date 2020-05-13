package app.pldt.appvno.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import app.pldt.appvno.AppVNOApplication
import app.pldt.appvno.firebase.MyFirebaseDatabase
import app.pldt.appvno.ui.call.CallDetailActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyFirebaseDatabase.isCalling.observe(this, Observer<Boolean> {
            if (it){
                displayIncomingCallAlert()
            }
        })
    }

    private fun displayIncomingCallAlert(){
        AlertDialog.Builder(this)
            .setTitle("Information")
            .setPositiveButton("Accept") { _, _ ->
                startActivity<CallDetailActivity>()
                toast("You cancel the call")

                // Make both state to connected
                //MyFirebaseDatabase.makeCallConnected()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // Delete call
                MyFirebaseDatabase.deleteCallState(AppVNOApplication.getInstance().tempUser!!)
                dialog.cancel()
            }
            .setCancelable(true)
            .setMessage("Some one is calling")
            .show()
    }
}