package com.egiwon.circularloopviewpager

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class PageChangeListener(
    private val tabLayout: TabLayout,
    private val helper: FragmentAdapterManger
): ViewPager.OnPageChangeListener {
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        val tab: TabLayout.Tab? = tabLayout.getTabAt(helper.convertTabIndex(position))
        tab?.select()
    }

    override fun onPageScrollStateChanged(state: Int) {

    }
}
