package com.egiwon.circularloopviewpager

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class PageChangeListener(
    private val tabLayout: TabLayout,
    private val helper: FragmentAdapterManger
): ViewPager.OnPageChangeListener {
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit
    override fun onPageScrollStateChanged(state: Int) = Unit

    override fun onPageSelected(position: Int) {
        val tab: TabLayout.Tab? = tabLayout.getTabAt(helper.convertTabIndex(position))
        tab?.select()

//        if (helper.getMenuData().isNotEmpty()) {
//            for (i in helper.getMenuData().indices) {
//                if ((helper as CircularLoopTabAdapter).hasInstance(i)) {
//                    val fragment: CurrentPage = (helper.getItem(i) as CurrentPage)
//                    if (helper.convertTabIndex(position) != i) {
//                        fragment.onSelectedPage(helper.convertTabIndex(position) == i)
//                    }
//                }
//            }
//
//        }
    }
}
