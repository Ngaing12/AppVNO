package app.pldt.appvno.ui.getStarted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import app.pldt.appvno.R
import app.pldt.appvno.common.SessionManager
import app.pldt.appvno.model.Onboarding
import app.pldt.appvno.ui.login.LoginActivity
import app.pldt.appvno.ui.loginRegister.LoginRegisterActivity
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_onboarding.*
import org.jetbrains.anko.startActivity

class OnBoardingActivity : AppCompatActivity() {

    private val onBoardingAdapter = OnboardingAdapter (
        listOf(
            Onboarding(
                "Something",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                R.drawable.success
            ),
            Onboarding(
                "Nothing",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                R.drawable.success
            ),
            Onboarding(
                "Gone",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                R.drawable.success
            ),
            Onboarding(
                "Finish",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                R.drawable.success
            )

        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        onBoarding_vp_content.adapter = onBoardingAdapter

        setupIndicators()
        setCurrentIndicator(0)
        setupListener()

    }

    private fun setupListener() {
        onBoarding_vp_content.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        onBoarding_tv_skip.setOnClickListener{
            SessionManager.setIsShownOnBoarding(true)
            startActivity<LoginActivity>()
            finish()
        }
    }

    private fun setupIndicators(){
        val indicator  = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParams : LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(24,0,24,0)
        for (i in indicator.indices){
            indicator[i] = ImageView(applicationContext)
            indicator[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_onboarding_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            onBoarding_ln_indicator.addView(indicator[i])
        }
    }

    private fun setCurrentIndicator(index : Int) {
        val childCount = onBoarding_ln_indicator.childCount
        for (i in 0 until childCount){
            val imageView = onBoarding_ln_indicator[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_onboarding_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_onboarding_inactive
                    )
                )
            }
        }
    }
}
