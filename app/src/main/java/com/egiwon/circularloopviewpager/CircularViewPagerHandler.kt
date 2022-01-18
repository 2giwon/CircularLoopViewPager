package com.egiwon.circularloopviewpager

import androidx.viewpager.widget.ViewPager

class CircularViewPagerHandler(
    private val viewPager: ViewPager,
    private val listener: ViewPager.OnPageChangeListener?
): ViewPager.OnPageChangeListener {

    init {
        viewPager.setCurrentItem(1, false)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        invokeOnPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    private fun invokeOnPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        listener?.onPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
        handleSetCurrentItemWithDelay(position)
        invokeOnPageSelected(position)
    }

    private fun handleSetCurrentItemWithDelay(position: Int) {
        viewPager.postDelayed({
            handleSetCurrentItem(position)
        }, SET_ITEM_DELAY)
    }

    private fun handleSetCurrentItem(position: Int) {
        val lastPosition = viewPager.adapter?.count?.minus(1) ?: return
        if (position == 0) {
            viewPager.setCurrentItem(lastPosition.minus(1), false)
        } else if (position == lastPosition) {
            viewPager.setCurrentItem(1, false)
        }
    }

    private fun invokeOnPageSelected(position: Int) {
        listener?.onPageSelected(position - 1)
    }

    override fun onPageScrollStateChanged(state: Int) {
        invokeOnPageScrollStateChanged(state)
    }

    private fun invokeOnPageScrollStateChanged(state: Int) {
        listener?.onPageScrollStateChanged(state)
    }

    companion object {
        const val SET_ITEM_DELAY = 300L
    }
}
