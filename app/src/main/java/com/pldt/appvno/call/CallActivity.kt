package com.pldt.appvno.call

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pldt.appvno.R
import com.pldt.appvno.home.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_call.*

class CallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        attachListener()

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(RecentFragment.newInstance(), "Recent")
        adapter.addFragment(KeypadFragment.newInstance(), "Keypad")
        adapter.addFragment(ContactFragment.newInstance(), "Contacts")
        view_pager_call.adapter = adapter

        tab_layout_call.setupWithViewPager(view_pager_call)

        tab_layout_call.getTabAt(0)?.setIcon(R.drawable.ic_launcher_foreground)
        tab_layout_call.getTabAt(1)?.setIcon(R.drawable.temp_ic_call_black_24dp)
        tab_layout_call.getTabAt(2)?.setIcon(R.drawable.temp_ic_date_range_black_24dp)
    }

    private fun attachListener() {

    }

}
