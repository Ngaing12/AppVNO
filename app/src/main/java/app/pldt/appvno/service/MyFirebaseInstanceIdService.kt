package app.pldt.appvno.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import app.pldt.appvno.R
import app.pldt.appvno.ui.SplashActivity
import app.pldt.appvno.ui.call.SysnetCallActivity
import app.pldt.appvno.ui.homePage.FreebeeHomeActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sysnetph.sysnetsdk.Sysnet
import com.sysnetph.sysnetsdk.activityListener
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class MyFirebaseInstanceIdService : FirebaseMessagingService()   {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("FCM", p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.d("FCM", "From ${remoteMessage.from}")
//
//        if (remoteMessage.notification == null ) {
//            remoteMessage.data.isEmpty().let {
//                Log.d("FCM", "Message data payload: " + remoteMessage.data)
//                Log.d("FCM", "Notification id: " + remoteMessage.data.get("notification_id"))
//                if *()
//                shwoNotification(remoteMessage.data["title"] ?: "No Title", remoteMessage.data["message"] ?: "No Content")
//            }
//        }

//        if (remoteMessage.data.size != 0) {
//            Log.d("FCM", "Message data payload: " + remoteMessage.data)
//            Log.d("FCM", "Notification id: " + remoteMessage.data["notification_id"?: "no id"])
//            shwoNotification(remoteMessage.data["title"] ?: "No Title", remoteMessage.data["message"] ?: "No Content")
//        }


//        if (remoteMessage.notification != null ) {
//            Log.d("FCM", "From ${remoteMessage.notification.toString()}")
//            shwoNotification(remoteMessage.notification?.title ?: "No Title", remoteMessage.notification?.body ?: "No Body")
//        }
//        remoteMessage.notification?.let {
//            Log.d("FCM", "From ${remoteMessage.notification.toString()}")
//            remoteMessage.data.isEmpty().let { _ ->
//               if (remoteMessage.data["type"] ?: 0 == 1 ) {
//                   // background only
//                   shwoNotification(it.title ?: "No Title", it.body ?: "No Body")
//               }
//            }
//        }
    }

    fun shwoNotification(title : String , message : String) {
        val i = intentFor<FreebeeHomeActivity>().clearTop()
        val intent = Intent(this,FreebeeHomeActivity::class.java)
        val channel_id = "app_vno"
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        var builder =  NotificationCompat.Builder(applicationContext, channel_id)
            .setSmallIcon(R.drawable.temp_logo)
            .setAutoCancel(true)
//            .setVibrate(long[] {1000,1000,1000,1000,1000})
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder = builder.setContent(getCustomDesign(title, message))
        } else {
            builder = builder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.temp_logo)
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channel_id,"app_vno",NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.setSound(uri, null)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0, builder.build())
    }

    private fun getCustomDesign(title : String , message : String) : RemoteViews {
        val remoteView = RemoteViews(applicationContext.packageName, R.layout.notifcation_custom_layout)
        remoteView.setTextViewText(R.id.notification_title, title)
        remoteView.setTextViewText(R.id.notification_message, message)
        remoteView.setImageViewResource(R.id.notification_icon, R.drawable.temp_logo)
        return  remoteView
    }
}