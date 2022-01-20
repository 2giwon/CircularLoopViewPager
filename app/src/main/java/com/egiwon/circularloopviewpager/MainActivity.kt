package com.egiwon.circularloopviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.egiwon.circularloopviewpager.databinding.ActivityMainBinding
import com.egiwon.circularloopviewpager.fragment.TestFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val tabTitles = listOf("A", "B", "C")

        binding.vpFragment.setPageCount(tabTitles.size)
        binding.vpFragment.setFragmentPagerAdapter(supportFragmentManager, object: CircularViewPager.FragmentItemListener {
            override fun getFragment(position: Int): Fragment {
                return TestFragment.newInstance(tabTitles[position])
            }

        })

//        binding.vpFragment.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tlTab))
//
//        tabTitles.forEach {
//            binding.tlTab.addTab(binding.tlTab.newTab().setText(it))
//        }
//
//        binding.tlTab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                binding.vpFragment.currentItem = tab.position
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
//            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
//
//        })


    }
}
