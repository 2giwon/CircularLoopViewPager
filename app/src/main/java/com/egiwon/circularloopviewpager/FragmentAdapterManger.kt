package com.egiwon.circularloopviewpager

interface FragmentAdapterManger {

    fun getStartingPagerIndex(): Int

    fun setMenuList(menuList: List<String>?)

    fun convertTabIndex(viewPagerIndex: Int): Int

    fun getLastAddedFragmentIndex(selectedPosition: Int, currentPosition: Int): Int

    fun getMenuData(): Collection<String>

    fun isShowingStartingTab(currentPosition: Int): Boolean

    fun getItemCount(): Int

    fun clear()
}
