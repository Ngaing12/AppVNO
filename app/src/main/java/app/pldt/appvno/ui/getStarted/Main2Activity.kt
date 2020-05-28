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
import app.pldt.appvno.ui.loginRegister.LoginRegisterActivity
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.startActivity

class Main2Activity : AppCompatActivity() {

    private val onBoardingAdapter = OnboardingAdapter (
        listOf(
            Onboarding(
                "Something",
                "Something Something Something",
                R.drawable.ic_launcher_foreground
            ),
            Onboarding(
                "Nothing",
                "Nothing Nothing Nothing",
                R.drawable.ic_launcher_foreground
            ),
            Onboarding(
                "Gone",
                "Gone Gone Gone",
                R.drawable.ic_launcher_foreground
            ),
            Onboarding(
                "Finish",
                "Finish Finish Finish",
                R.drawable.ic_launcher_foreground
            )

        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        vp_onboarding.adapter = onBoardingAdapter
        setupIndicators()
        setCurrentIndicator(0)
        vp_onboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        tv_skip.setOnClickListener{
            startActivity<LoginRegisterActivity>()
            finish()
        }
    }

    private fun setupIndicators(){
        val indicator  = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParams : LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
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
            ln_indicator.addView(indicator[i])
        }
    }

    private fun setCurrentIndicator(index : Int) {
        val childCount = ln_indicator.childCount
        for (i in 0 until childCount){
            val imageView = ln_indicator[i] as ImageView
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
