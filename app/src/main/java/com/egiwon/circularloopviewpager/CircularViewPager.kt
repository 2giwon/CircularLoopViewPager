package com.egiwon.circularloopviewpager

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class CircularViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    private var pageCount = 0
    private val listener: CircularViewPagerListener by lazy {
        CircularViewPagerListener()
    }

    interface FragmentItemListener {
        fun getFragment(position: Int): Fragment
    }


    init {
        getAttributes(attrs)
        overScrollMode = OVER_SCROLL_NEVER
        offscreenPageLimit = 1
    }

    private fun getAttributes(attrs: AttributeSet?) {
        val a: TypedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.CircularViewPager, 0, 0)
        pageCount = a.getInt(R.styleable.CircularViewPager_pageCount, 0)
        a.recycle()
    }

    fun setFragmentPagerAdapter(manager: FragmentManager, listener: FragmentItemListener) {
        val adapter: CircularFragmentPagerAdapter = object: CircularFragmentPagerAdapter(manager, pageCount) {
            override fun getFragment(position: Int): Fragment {
                return listener.getFragment(position)
            }
        }

        setAdapter(adapter)
    }

    fun setPageCount(count: Int) {
        pageCount = count
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        super.setAdapter(adapter)
        addOnPageChangeListener(listener)
        currentItem = 1
    }

    inner class CircularViewPagerListener: OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

        override fun onPageSelected(position: Int) {
            postDelayed({
                loopCurrentItem(position)
            }, DELAY)
        }

        override fun onPageScrollStateChanged(state: Int) = Unit

        private fun loopCurrentItem(position: Int) {
            val pageCount = adapter?.count ?: 0
            if (position == pageCount - 1) {
                setCurrentItem(1, false)
            } else if (position == 0) {
                setCurrentItem(pageCount - 2, false)
            }
        }
    }

    companion object {
        const val DELAY = 200L
    }

}
