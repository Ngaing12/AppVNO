package com.pldt.appvno.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pldt.appvno.R
import com.pldt.appvno.extensions.isVisible
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

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

        navigation_bottom_home.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home_bottomNavigation -> {
                    replaceFragment(HomeFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_messages_bottomNavigation -> {
                    replaceFragment(MessageFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_call_bottomNavigation -> {
                    replaceFragment(CallFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_shop_bottomNavigation -> {
                    replaceFragment(ShopFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_others_bottomNavigation -> {
                    replaceFragment(OtherFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container_fragment_home, fragment)
            addToBackStack(null)
            commit()
        }
    }
}
