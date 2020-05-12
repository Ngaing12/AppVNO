package app.pldt.appvno.googleAds

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.pldt.appvno.common.TEST_AD_UNIT_ID_MANAGER
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback


object GoogleAdsManager {

    private val isLoading = MutableLiveData<Boolean>()
    fun getIsLoading(): LiveData<Boolean> =
        isLoading

    lateinit  var mContext: Context
    lateinit var  mRewardedAd: RewardedAd

    fun init(context: Context) {
        mContext = context
        MobileAds.initialize(mContext) {}
        loadNewAds()
    }

    fun loadNewAds(){
        mRewardedAd =
            createAndLoadRewardedAd(
                TEST_AD_UNIT_ID_MANAGER
            )
    }

    private fun createAndLoadRewardedAd(adUnitId : String): RewardedAd {
        isLoading.value = true
        val rewardedAd = RewardedAd(mContext, adUnitId)
        val adLoadCallback = object: RewardedAdLoadCallback() {
            override fun onRewardedAdLoaded() {
                // Ad successfully loaded.
                isLoading.postValue(false)
                Log.d("Something", "Ads Ready")
            }
            override fun onRewardedAdFailedToLoad(errorCode: Int) {
                // Ad failed to load.
                isLoading.postValue(false)
                Log.d("Something", "Failed to loads ads")
            }
        }
        // This is use for AD Mob
        // rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
        // This is use for Ad manager
        rewardedAd.loadAd(PublisherAdRequest.Builder().build(), adLoadCallback)
        return rewardedAd
    }
}