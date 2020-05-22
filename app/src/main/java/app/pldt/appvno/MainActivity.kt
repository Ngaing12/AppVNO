package app.pldt.appvno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import app.pldt.appvno.ui.call.CallActivity
import app.pldt.appvno.googleAds.GoogleAdsManager
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.ui.call.SysnetCallActivity
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.sysnetph.sysnetsdk.RegistrationAction
import com.sysnetph.sysnetsdk.Sysnet
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private lateinit var rewardedAd: RewardedAd

    private var isRewarded = false
    private var counter = 0
    private val MAX_RETRY = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeUi()
        attachListener()

        rewardedAd = GoogleAdsManager.mRewardedAd
    }

    private fun attachListener() {
        mainActivity_btn_showAds.setOnClickListener {
            if (rewardedAd.isLoaded) {
                showAds()
            }
            else {
                toast( "The rewarded ad wasn't loaded yet.")
            }
        }
        mainActivity_btn_back.setOnClickListener {
            finish()
        }
    }

    private fun subscribeUi() {
        GoogleAdsManager.getIsLoading().observe(this, Observer<Boolean> { isLoading ->
            isLoading?.let { it ->
                rewardedAd = GoogleAdsManager.mRewardedAd
                if (!it){
                    if (counter < MAX_RETRY) {
                        if (!rewardedAd.isLoaded){
                            GoogleAdsManager.loadNewAds()
                            Log.d("Something", counter.toString())
                            counter++
                        }else {
                            loading(it)
                        }

                    }
                    else {
                        // Show the try again later
                        showRetry()
                    }
                }
                else { loading(it) }

            }
        })
    }

    private fun showAds(){
        val adCallback = object: RewardedAdCallback() {
            override fun onRewardedAdOpened() {
                // Ad opened.
            }
            override fun onRewardedAdClosed() {
                // Ad closed.
                if (isRewarded) {
                    startActivity<SysnetCallActivity>()
                }else {
                    startActivity<CallActivity>()
                }
                GoogleAdsManager.loadNewAds()
                finish()
            }
            override fun onUserEarnedReward(@NonNull reward: RewardItem) {
                // User earned reward.
                toast("You have earned reward ${reward.amount}")
                isRewarded = true
                AppVNOApplication.getInstance().addPoints(reward.amount)

                // Open FreeCall
            }
            override fun onRewardedAdFailedToShow(errorCode: Int) {
                // Ad failed to display.
                toast("Something went wrong")
                GoogleAdsManager.loadNewAds()
                finish()
            }
        }
        rewardedAd.show(this, adCallback)
    }

    private fun loading(value : Boolean){
        mainActivity_group_wait.isVisible(value)
        mainActivity_group_ready.isVisible(!value)
        mainActivity_group_retry.isVisible(false)
    }

    private fun showRetry(){
        mainActivity_group_wait.isVisible(false)
        mainActivity_group_ready.isVisible(false)
        mainActivity_group_retry.isVisible(true)
    }

}
