package com.egiwon.circularloopviewpager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.egiwon.circularloopviewpager.R
import com.egiwon.circularloopviewpager.databinding.LayoutFragmentBinding

class TestFragment: Fragment() {

    private lateinit var binding: LayoutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val text = it.getString(TEXT, "")
            binding.tvText.text = text
        }
    }

    companion object {
        const val TEXT = "text"

        fun newInstance(text: String): TestFragment {
            val args = Bundle()
            args.putString(TEXT, text)

            val fragment = TestFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
