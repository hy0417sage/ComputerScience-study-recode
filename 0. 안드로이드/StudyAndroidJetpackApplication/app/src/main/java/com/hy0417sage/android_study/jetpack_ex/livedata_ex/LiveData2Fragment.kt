package com.hy0417sage.android_study.jetpack_ex.livedata_ex

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hy0417sage.android_study.R
import com.hy0417sage.android_study.databinding.FragmentLivedataTest1Binding
import com.hy0417sage.android_study.databinding.FragmentLivedataTest2Binding

class LiveData2Fragment : Fragment() {

    private val TAG = "LiveDataFragment2"

    private var _binding: FragmentLivedataTest2Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLivedataTest2Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

}