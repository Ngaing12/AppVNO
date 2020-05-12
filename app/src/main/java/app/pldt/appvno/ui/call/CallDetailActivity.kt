package app.pldt.appvno.ui.call

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.annotation.StringRes
import app.pldt.appvno.R
import app.pldt.appvno.ui.profile.onRequestPermissionsResult
import app.pldt.appvno.ui.termsCondition.TermsConditionActivity
import com.opentok.android.*
import kotlinx.android.synthetic.main.activity_call_detail.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import permissions.dispatcher.*


const val RC_VIDEO_APP_PERM = 124

@RuntimePermissions
class CallDetailActivity : AppCompatActivity(),Session.SessionListener, PublisherKit.PublisherListener,
    SubscriberKit.SubscriberListener  {


    val API_KEY = "46732122"
    val SESSION_ID = "2_MX40NjczMjEyMn5-MTU4OTI1MjA3MjQ1NX41cHhHczdiZEE2MExidi9WYzFVaERDdVF-fg"
    val TOKEN = "T1==cGFydG5lcl9pZD00NjczMjEyMiZzaWc9MjliNTEzNTM1MzAxZmExZDFhZGJjNTVmNGVlMzQxZjAwZjUxNjk0ZDpzZXNzaW9uX2lkPTJfTVg0ME5qY3pNakV5TW41LU1UVTRPVEkxTWpBM01qUTFOWDQxY0hoSGN6ZGlaRUUyTUV4aWRpOVdZekZWYUVSRGRWRi1mZyZjcmVhdGVfdGltZT0xNTg5MjUyMTI5Jm5vbmNlPTAuMDk4OTE3MDQ4NTM4NDY0Mjgmcm9sZT1wdWJsaXNoZXImZXhwaXJlX3RpbWU9MTU5MTg0NDEyNyZpbml0aWFsX2xheW91dF9jbGFzc19saXN0PQ=="
    val LOG_TAG = "Test"

    lateinit var mSession : Session
    var mPublisher : Publisher? = null
    var mSubscriber: Subscriber? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_detail)

        close_video_chat_btn.setOnClickListener {
            // Remove call in the db
            destroyCall()
            finish()
        }
        requestCallPermissionWithPermissionCheck()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated method
         onRequestPermissionsResult(requestCode, grantResults)
    }

    @NeedsPermission(Manifest.permission.CAMERA, Manifest.permission.INTERNET,Manifest.permission.RECORD_AUDIO)
    fun requestCallPermission() {
        // 1
        mSession =  Session.Builder(this, API_KEY, SESSION_ID).build()
        mSession.setSessionListener(this)
        mSession.connect(TOKEN)
    }

    @OnPermissionDenied(Manifest.permission.CAMERA, Manifest.permission.INTERNET,Manifest.permission.RECORD_AUDIO)
    fun onContactsDenied() {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        // or disabling certain functionality
        toast("You need to allow all Permission to proceed")
    }

    @OnShowRationale(Manifest.permission.CAMERA, Manifest.permission.INTERNET,Manifest.permission.RECORD_AUDIO)
    fun showRationaleForContacts(request: PermissionRequest) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        showRationaleDialog(R.string.temp_permission_contacts_rationale, request)
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA, Manifest.permission.INTERNET,Manifest.permission.RECORD_AUDIO)
    fun onContactsNeverAskAgain() {
        //showSettingsDialog()
        destroyCall()
        finish()
    }


    private fun showRationaleDialog(@StringRes messageResId: Int, request: PermissionRequest) {

        AlertDialog.Builder(this)
            .setTitle("Permissions")
            .setPositiveButton("Proceed") { _, _ ->  request.proceed() }
            .setNegativeButton("Cancel") { _, _ -> request.cancel() }
            .setCancelable(false)
            .setMessage(messageResId)
            .show()
    }


    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("GOTO SETTINGS",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
                openSettings()
            })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()

    }

    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.setData(uri)
        startActivityForResult(intent, 101)
    }


    fun destroyCall(){
        // Remove data from db
        // Close app
        mPublisher?.destroy()
        Log.d(LOG_TAG, "Stream Drop")
        if (mSubscriber != null){
            mSubscriber = null
            subscriber_container.removeAllViews()
        }
    }

    //  2 Publisher
    override fun onConnected(p0: Session?) {
        Log.d(LOG_TAG, "Session Connected")
        tv_call_status.text = "Connected"
        mPublisher = Publisher.Builder(this)
            .videoTrack(false)
            .build()
        mPublisher?.setPublisherListener(this)

        publisher_container.addView(mPublisher?.view)
        if (mPublisher?.view is GLSurfaceView) {
            (mPublisher?.view as GLSurfaceView).setZOrderOnTop(true)
        }

        mSession.publish(mPublisher)
    }

    override fun onError(p0: Session?, p1: OpentokError?) {
        Log.d(LOG_TAG, "Stream Error")
    }

    override fun onStreamDropped(p0: Session?, p1: Stream?) {
       destroyCall()
      toast("Call partner lost connection")
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyCall()
    }

    // 3
    override fun onStreamReceived(p0: Session?, p1: Stream?) {
        Log.d(LOG_TAG, "Stream Recieved")

        if (mSubscriber == null){
            mSubscriber = Subscriber.Builder(this, p1).build()
            mSubscriber?.setSubscriberListener(this)
            mSession.subscribe(mSubscriber)
            subscriber_container.addView(mSubscriber?.view)

        }
    }

    override fun onDisconnected(p0: Session?) {
        Log.d(LOG_TAG, "onDisconnected: Disconnected from session")
    }

    override fun onError(p0: PublisherKit?, p1: OpentokError?) {
        Log.d(LOG_TAG, " PublisherKit Error")
    }

    override fun onStreamCreated(p0: PublisherKit?, p1: Stream?) {
        Log.d(LOG_TAG, " PublisherKit streamCreated")
    }

    override fun onStreamDestroyed(p0: PublisherKit?, p1: Stream?) {
        Log.d(LOG_TAG, " stream destroy")
    }

    override fun onConnected(p0: SubscriberKit?) {
        Log.d(LOG_TAG, " SubscriberKit connected")
    }

    override fun onDisconnected(p0: SubscriberKit?) {
        Log.d(LOG_TAG, " SubscriberKit disconnected")

    }

    override fun onError(p0: SubscriberKit?, p1: OpentokError?) {
        Log.d(LOG_TAG, " SubscriberKit error")
    }

}
