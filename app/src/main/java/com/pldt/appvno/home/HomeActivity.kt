package com.pldt.appvno.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.pldt.appvno.R
import com.pldt.appvno.extensions.isVisible
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {


    var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        attachListener()
        replaceFragment(HomeFragment.newInstance())
    }

    private fun attachListener() {

        img_open_home.setOnClickListener {
            container_floating_home.isVisible(true)
        }

        img_close_home.setOnClickListener {
            container_floating_home.isVisible(false)
        }

        btn_free_call_home.setOnClickListener{
            // Open Activity For calling
        }

        btn_premium_call_home.setOnClickListener {
            // Open Activity For calling
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
                    container_fragment_home.currentItem = it.itemId
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_messages_bottomNavigation -> {
                    container_fragment_home.currentItem = it.itemId
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_call_bottomNavigation -> {
                    container_fragment_home.currentItem = it.itemId
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_shop_bottomNavigation -> {
                    container_fragment_home.currentItem = it.itemId
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_others_bottomNavigation -> {
                    container_fragment_home.currentItem = it.itemId
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

}
