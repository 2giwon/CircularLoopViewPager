package com.egiwon.circularloopviewpager

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class LoopingViewPager : ViewPager {

    protected var isInfinite = true
    private var currentPagePosition = 0

    private var previousScrollState = SCROLL_STATE_IDLE
    private var scrollState = SCROLL_STATE_IDLE

    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        val attribute = context.theme.obtainStyledAttributes(attrs, R.styleable.LoopingViewPager, 0, 0)

        try {
            isInfinite = attribute.getBoolean(R.styleable.LoopingViewPager_isInfinite, false)
        } finally {
            attribute.recycle()
        }

        init()

    }

    private fun init() {
        addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                currentPagePosition = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                previousScrollState = state
                scrollState = state
                if (state == SCROLL_STATE_IDLE) {
                    if (isInfinite) {
                        if (adapter == null) return
                        val itemCount = adapter?.count ?: 0
                        if (itemCount < 2) {
                            return
                        }

                        val index = currentItem
                        if (index == 0) {
                            setCurrentItem(itemCount - 2, false)
                        } else if (index == itemCount - 1) {
                            setCurrentItem(1, false)
                        }
                    }
                }
            }

        })

        if (isInfinite) setCurrentItem(1, false)
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        super.setAdapter(adapter)
        if (isInfinite) setCurrentItem(1, false)
    }

    fun reset() {
        currentPagePosition = if (isInfinite) {
            setCurrentItem(1, false)
            1
        } else {
            setCurrentItem(0, false)
            0
        }
    }
    
    private fun getRealPosition(position: Int) : Int {
        if (!isInfinite || adapter == null) return position
        return when {
            position == 0 -> requireNotNull(adapter).count - 3
            position > requireNotNull(adapter).count - 2 -> 0
            else -> position - 1
        }
    }
}
