package com.egiwon.circularloopviewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

abstract class CircularFragmentPagerAdapter(
    fragmentManager: FragmentManager, count: Int
): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pageList = mutableListOf<Int>()

    init {
        pageList.add(count + 1)
        for (i in 0 until count) {
            pageList.add(i + 1)
        }
        pageList.add(0)
    }

    override fun getCount(): Int = if (pageList.size > 2) pageList.size else 0

    override fun getItem(position: Int): Fragment {
        if (position == pageList.size - 1) {
            return getFragment(0)
        } else if (position == 0) {
            return getFragment(pageList.size - 3)
        }
        return getFragment(pageList[position] - 1)
    }

    protected abstract fun getFragment(position: Int): Fragment

}
