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

        val tabTitles = listOf("A", "B", "C", "D")

        val adapter = CircularLoopTabAdapter(supportFragmentManager)
        binding.vpFragment.adapter = adapter
        binding.vpFragment.addOnPageChangeListener(
            PageChangeListener(binding.tlTab, adapter)
        )

        tabTitles.forEach {
            binding.tlTab.addTab(binding.tlTab.newTab().setText(it))
        }

        adapter.setMenuList(tabTitles)
        adapter.notifyDataSetChanged()

        val tabSelectedListener = TabSelectedListener(binding.vpFragment, adapter, this@MainActivity)
        binding.vpFragment.currentItem = adapter.getStartingPagerIndex()
        binding.tlTab.addOnTabSelectedListener(tabSelectedListener)
    }
}
