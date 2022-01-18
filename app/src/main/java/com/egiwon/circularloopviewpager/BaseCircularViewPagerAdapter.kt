package com.egiwon.circularloopviewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

abstract class BaseCircularViewPagerAdapter<ITEM : Any>(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val items = mutableListOf<ITEM>()

    protected abstract fun getFragmentForItem(item: ITEM): Fragment

    override fun getItem(position: Int): Fragment {
        val itemSize = items.size
        return when (position) {
            0 -> {
                getFragmentForItem(items[itemSize - 1])
            }
            itemSize + 1 -> {
                getFragmentForItem(items[0])
            }
            else -> {
                getFragmentForItem(items[position - 1])
            }
        }
    }

    override fun getCount(): Int {
        val itemSize = items.size
        return if (itemSize > 1) itemSize + 2 else itemSize
    }

    fun getCountWithoutFakePages(): Int {
        return items.size
    }

    fun setItems(items: List<ITEM>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

