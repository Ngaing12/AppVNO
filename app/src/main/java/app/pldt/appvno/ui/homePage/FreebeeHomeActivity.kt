package app.pldt.appvno.ui.homePage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import app.pldt.appvno.R
import kotlinx.android.synthetic.main.activity_freebee_home.*
import org.jetbrains.anko.toast

class FreebeeHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freebee_home)

        setCurrentFragment(FreebeeHomeFragment.newInstance())

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> setCurrentFragment(FreebeeHomeFragment.newInstance())
                R.id.miContacts -> setCurrentFragment(FreebeeContactsFragment.newInstance())
                R.id.miCalls -> setCurrentFragment(FreebeeCallFragment.newInstance())
                R.id.miMessages -> setCurrentFragment(FreebeeMessagesFragment.newInstance())
                R.id.miMenu -> toast("Click Menu")
            }
            true
        }
    }

    private fun setCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_container, fragment)
            commit()
        }
}
