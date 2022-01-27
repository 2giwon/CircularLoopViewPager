package com.egiwon.circularloopviewpager

import androidx.fragment.app.Fragment

interface FragmentCreator {
    fun getInstance(position: Int): Fragment
    fun hasInstance(position: Int): Boolean
}
