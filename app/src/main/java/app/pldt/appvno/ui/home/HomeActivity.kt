package app.pldt.appvno.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import app.pldt.appvno.MainActivity
import app.pldt.appvno.R
import app.pldt.appvno.ui.call.CallActivity
import app.pldt.appvno.extensions.isVisible
import app.pldt.appvno.firebase.MyFirebaseDatabase
import app.pldt.appvno.ui.BaseActivity
import app.pldt.appvno.ui.message.MessageFragment
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : BaseActivity(), HomeFragment.OnHomeInteractionListener {

    var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        attachListener()
        replaceFragment(HomeFragment.newInstance())
    }

    private fun attachListener() {

        btn_free_call_home.setOnClickListener {
            container_floating_home.isVisible(false)

            startActivity<MainActivity>()
        }

        img_close_home.setOnClickListener {
            container_floating_home.isVisible(false)
        }

        btn_premium_call_home.setOnClickListener {
            container_floating_home.isVisible(false)
            startActivity<CallActivity>()
        }


        container_fragment_home.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem?.isChecked = false
                } else {
                    navigation_bottom_home.menu.getItem(0).isChecked = false
                }
                Log.d("page", "onPageSelected: $position")
                navigation_bottom_home.menu.getItem(position).isChecked = true
                prevMenuItem = navigation_bottom_home.menu.getItem(position)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        navigation_bottom_home.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home_bottomNavigation -> {
                    container_fragment_home.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_messages_bottomNavigation -> {
                    container_fragment_home.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_call_bottomNavigation -> {
                    container_fragment_home.currentItem = 2
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_shop_bottomNavigation -> {
                    container_fragment_home.currentItem = 3
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_others_bottomNavigation -> {
                    container_fragment_home.currentItem = 4
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }

        setupViewPager()
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container_fragment_home, fragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment.newInstance())
        adapter.addFragment(MessageFragment.newInstance())
        adapter.addFragment(CallFragment.newInstance())
        adapter.addFragment(ShopFragment.newInstance())
        adapter.addFragment(OtherFragment.newInstance())
        container_fragment_home.adapter = adapter
    }

    override fun onClickCall() {
        container_floating_home.isVisible(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        MyFirebaseDatabase.removeListener()
    }
}
