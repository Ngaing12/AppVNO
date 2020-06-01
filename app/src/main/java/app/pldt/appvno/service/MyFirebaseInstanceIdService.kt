package app.pldt.appvno.service

import android.util.Log
import app.pldt.appvno.ui.call.SysnetCallActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sysnetph.sysnetsdk.Sysnet
import com.sysnetph.sysnetsdk.activityListener
import org.jetbrains.anko.startActivity

class MyFirebaseInstanceIdService : FirebaseMessagingService()  , activityListener {

    override fun onCreate() {
        super.onCreate()
        Sysnet.getInstance().calllistener = this
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("FCM", p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.d("FCM", remoteMessage.data.toString())
    }

    override fun onCallActivity() {
        Log.d("FCM", "Recieved Call")
        startActivity<SysnetCallActivity>("incoming" to true)
    }

    override fun onIncomingActivity() {
        TODO("Not yet implemented")
    }

    override fun onOutgoing() {
        TODO("Not yet implemented")
    }
}