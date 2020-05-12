package app.pldt.appvno.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private var mFragmentList = mutableListOf<Fragment>()
    private var mFragmentTitle = mutableListOf<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

    fun addFragment(fragment: Fragment, title : String) {
        mFragmentList.add(fragment)
        mFragmentTitle.add(title)
    }
    override fun getPageTitle(position: Int): CharSequence? {
        // return super.getPageTitle(position)
        return  mFragmentTitle[position]
    }
}