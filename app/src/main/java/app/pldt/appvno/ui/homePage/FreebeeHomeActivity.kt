package app.pldt.appvno.ui.homePage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import app.pldt.appvno.R
import app.pldt.appvno.ui.shop.ShopFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_freebee_home.*
import org.jetbrains.anko.toast

class FreebeeHomeActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freebee_home)

        setupBottomNavigation()
        setupNavigationDrawer()
        setCurrentFragment(FreebeeHomeFragment.newInstance())


    }


    private fun setupNavigationDrawer() {
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        navigationDrawer.setNavigationItemSelectedListener(this)
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.menu.getItem(4).isCheckable = false
        bottomNavigationView.menu.getItem(4).setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                drawer_layout.openDrawer(GravityCompat.END)
                return true
            }
        })


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> setCurrentFragment(FreebeeHomeFragment.newInstance())
                R.id.miContacts -> setCurrentFragment(FreebeeContactsFragment.newInstance())
                R.id.miCalls -> setCurrentFragment(FreebeeCallFragment.newInstance())
                R.id.miMessages -> setCurrentFragment(FreebeeMessagesFragment.newInstance())
            }
            true
        }
    }

    private fun setCurrentFragment(fragment : Fragment, x : Int = 0) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_container, fragment)
            commit()
            if (x == 1 ) {
                val size = bottomNavigationView.menu.size()
                for (i in 0 until size) {
                    bottomNavigationView.menu.getItem(i).isChecked = false;
                }
            }
        }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.drawer_profile -> {
                setCurrentFragment(ShopFragment.newInstance(),1)
            }
            R.id.drawer_shop -> {
                setCurrentFragment(ShopFragment.newInstance(),1)
            }
        }


        drawer_layout.closeDrawer(GravityCompat.END)
        return true
    }
}
