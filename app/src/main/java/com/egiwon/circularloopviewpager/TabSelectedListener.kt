package com.egiwon.circularloopviewpager

import android.content.Context
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class TabSelectedListener(
    private val viewPager: ViewPager,
    private val helper: CircularLoopTabAdapter,
    private val context: Context
): TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }
}
