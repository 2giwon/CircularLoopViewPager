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


    }
}
