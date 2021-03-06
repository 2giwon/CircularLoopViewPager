package com.egiwon.circularloopviewpager

import androidx.fragment.app.Fragment
import com.egiwon.circularloopviewpager.fragment.TestFragment

class BaseFragmentAdapterHelper: FragmentAdapterManger, FragmentCreator {

    private val _menuData: MutableList<String> = mutableListOf()
    val menuData: List<String> get() = _menuData

    private val tabTitleFragmentMap = mutableMapOf<String, Fragment?>()

    val tabIndexViewPagerIndexMap = mutableMapOf<Int, MutableList<Int>>()

    override fun getStartingPagerIndex(): Int {
        val menuSize = _menuData.size
        val startingTabIndex = 0

        return if (mayScrollInfinitely()) 500 * menuSize else startingTabIndex
    }

    override fun setMenuList(menuList: List<String>?) {
        _menuData.clear()
        menuList?.let(_menuData::addAll)

        tabIndexViewPagerIndexMap.clear()
        for (i in menuData.indices) {
            tabIndexViewPagerIndexMap[i] = mutableListOf()
        }
    }

    override fun convertTabIndex(viewPagerIndex: Int): Int {
        return if (_menuData.isEmpty()) 0 else viewPagerIndex % _menuData.size
    }

    override fun getLastAddedFragmentIndex(selectedPosition: Int, currentPosition: Int): Int {
        val viewPagerIndexList = tabIndexViewPagerIndexMap[selectedPosition]

        return if (viewPagerIndexList.isNullOrEmpty()) {
            currentPosition / menuData.size * menuData.size + selectedPosition
        } else {
            viewPagerIndexList.first()
        }
    }

    override fun getMenuData(): Collection<String> {
        return ArrayList(_menuData)
    }

    override fun isShowingStartingTab(currentPosition: Int): Boolean {
        return try {
            convertTabIndex(currentPosition) == 0
        } catch (e: Exception) {
            false
        }
    }

    override fun getItemCount(): Int {
        val menuSize = _menuData.size
        return if (mayScrollInfinitely()) 1000 * menuSize else menuSize
    }

    override fun clear() {
        _menuData.clear()
        tabTitleFragmentMap.clear()
        tabIndexViewPagerIndexMap.clear()
    }

    override fun getInstance(position: Int): Fragment {
        val tabIndex = convertTabIndex(position)
        if (tabIndex < _menuData.size) {
            val title = _menuData[tabIndex]
            var savedFragment: Fragment? = tabTitleFragmentMap[title]
            if (savedFragment == null) {
                savedFragment = createNewInstance(tabIndex)
                tabTitleFragmentMap[title] = savedFragment
            }
            return savedFragment
        }
        return TestFragment.newInstance(_menuData[0])
    }

    private fun createNewInstance(position: Int): Fragment {
        return try {
            TestFragment.newInstance(_menuData[position])
        } catch (e: Exception) {
            TestFragment.newInstance(_menuData[0])
        }
    }

    override fun hasInstance(position: Int): Boolean {
        val tabIndex = convertTabIndex(position)
        if (tabIndex >= _menuData.size) return false
        val title = _menuData[tabIndex]
        return tabTitleFragmentMap[title] != null
    }

    private fun mayScrollInfinitely(): Boolean = _menuData.size > 2
}
