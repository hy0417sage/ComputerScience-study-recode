package com.hy0417sage.android_study.jetpack_ex.binding_ex.databinding_ex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hy0417sage.android_study.R
import com.hy0417sage.android_study.databinding.FragmentDatabindingTestBinding

class DataBindingFragment : Fragment() {

    lateinit var binding : FragmentDatabindingTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_databinding_test, container, false)

        binding.dataBindingTest.text = "fragment data binding text 입니다"
        return binding.root
    }

}