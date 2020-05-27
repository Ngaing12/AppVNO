package app.pldt.appvno.ui.call

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.pldt.appvno.AppVNOApplication
import app.pldt.appvno.R
import com.sysnetph.sysnetsdk.Sysnet
import com.sysnetph.sysnetsdk.activityListener
import com.sysnetph.sysnetsdk.callactionsListener
import kotlinx.android.synthetic.main.activity_sysnet_call.*
import org.jetbrains.anko.makeCall

class SysnetCallActivity : AppCompatActivity() , activityListener, callactionsListener {

    private var isLoading = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sysnet_call)



        Sysnet.getInstance().calllistener = this;
        Sysnet.getInstance().callactionListener = this;

        val incomingCall = intent.getBooleanExtra("incoming", false)

        if (incomingCall) {
            tv_call_status.setText("Waiting For answer");
            group_outGOing.setVisibility(View.GONE)
            group_callingOther.setVisibility(View.GONE)
            group_incoming.setVisibility(View.VISIBLE)
        }

        setupButtonAction()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Sysnet.getInstance().endCall()
    }

    private fun setupButtonAction() {

        img_logout.setOnClickListener {
            finish()
        }

        btn_callOther.setOnClickListener {

            if (isLoading)
                return@setOnClickListener
            callUser()
        }

        btn_endCall.setOnClickListener {
            Sysnet.getInstance().endCall()
        }

        btn_endCall2.setOnClickListener {
            Sysnet.getInstance().decline()
        }

        btn_answerCall.setOnClickListener {
            Sysnet.getInstance().answerCall()
        }
        btn_speaker.setOnClickListener {
            Sysnet.getInstance().toggleSpeaker(applicationContext,true)
        }
        btn_speakerOff.setOnClickListener {
            Sysnet.getInstance().toggleSpeaker(applicationContext,false)
        }
        btn_mute.setOnClickListener {
            Sysnet.getInstance().toggleMute(true)
        }
        btn_unmute.setOnClickListener {
            Sysnet.getInstance().toggleMute(false)
        }
    }

    fun callUser() {
        isLoading = true
        // TODO - temp
        if (Sysnet.getInstance().userlogin) {
            if (AppVNOApplication.getInstance().tempUser?.number == "9000000000") {
                Sysnet.getInstance().makeCall("ian2", Sysnet.Servicetype.Free, Sysnet.NetworkType.ONNET)
            } else {
                Sysnet.getInstance().makeCall("ian", Sysnet.Servicetype.Free, Sysnet.NetworkType.ONNET)
            }
        }
    }


    // Pre call
    override fun onCallActivity() {
        tv_call_status.setText("On Call");
        group_outGOing.setVisibility(View.VISIBLE)
        group_callingOther.setVisibility(View.GONE)
        group_incoming.setVisibility(View.GONE)
    }

    override fun onIncomingActivity() {
        tv_call_status.setText("Waiting For answer");
        group_outGOing.setVisibility(View.GONE)
        group_callingOther.setVisibility(View.GONE)
        group_incoming.setVisibility(View.VISIBLE)
    }

    override fun onOutgoing() {
        group_outGOing.setVisibility(View.VISIBLE)
        group_callingOther.setVisibility(View.GONE)
        group_incoming.setVisibility(View.GONE)
        isLoading = false;
    }


    // On Call
    override fun ResumeCall() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun EndCall() {
        group_outGOing.setVisibility(View.GONE)
        group_callingOther.setVisibility(View.VISIBLE)
        group_incoming.setVisibility(View.GONE)
        isLoading = false;
    }

    override fun HoldCall() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
