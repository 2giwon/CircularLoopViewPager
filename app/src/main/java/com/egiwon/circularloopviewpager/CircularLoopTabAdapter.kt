package com.egiwon.circularloopviewpager

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter

class CircularLoopTabAdapter(
    private val fragmentManager: FragmentManager,
    private val helper: BaseFragmentAdapterHelper = BaseFragmentAdapterHelper()
) : PagerAdapter(), FragmentAdapterManger by helper, FragmentCreator by helper {

    private var currentTransaction: FragmentTransaction? = null
    private var currentPrimaryItem: Fragment? = null

    override fun getCount(): Int = helper.getItemCount()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (`object` as Fragment).view === view
    }

    private fun getTag(tabPosition: Int): String? =
        if (tabPosition < helper.menuData.size) helper.menuData[tabPosition] else null

    override fun clear() {
        helper.clear()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val tabPosition = helper.convertTabIndex(position)
        val tag: String? = getTag(tabPosition)

        val fragment: Fragment? = tag?.let {
            fragmentManager.findFragmentByTag(it)
        }

        if (fragment != null) {
            return fragment
        }

        if (currentTransaction == null) {
            currentTransaction = fragmentManager.beginTransaction()
        }

        val stateFragment = helper.getInstance(position)

        stateFragment.setMenuVisibility(false)
        currentTransaction?.add(container.id, stateFragment, tag)
        currentTransaction?.setMaxLifecycle(stateFragment, Lifecycle.State.STARTED)

        return stateFragment
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        val fragment = `object` as Fragment

        try {
            if (fragment != currentPrimaryItem) {
                if (currentPrimaryItem != null) {
                    currentPrimaryItem?.setMenuVisibility(false)
                    if (currentTransaction == null) {
                        currentTransaction = fragmentManager.beginTransaction()
                    }
                    currentTransaction?.setMaxLifecycle(
                        requireNotNull(currentPrimaryItem), Lifecycle.State.STARTED
                    )
                }

                fragment.setMenuVisibility(true)
                if (currentTransaction == null) {
                    currentTransaction = fragmentManager.beginTransaction()
                }

                currentTransaction?.setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
                currentPrimaryItem = fragment
            }
        } catch (e: Exception) {

        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        try {
            val tag = getTag(convertTabIndex(position))
            val fragment = fragmentManager.findFragmentByTag(tag)
            fragment?.let {
                if (currentTransaction == null) {
                    currentTransaction = fragmentManager.beginTransaction()
                }

                currentTransaction?.remove(it)
                if (fragment === currentPrimaryItem) {
                    currentPrimaryItem = null
                }
            }
        } catch (e: Exception) {

        }
    }

    override fun finishUpdate(container: ViewGroup) {
        if (currentTransaction != null) {
            currentTransaction?.commitNowAllowingStateLoss()
            currentTransaction = null
        }
    }
}
