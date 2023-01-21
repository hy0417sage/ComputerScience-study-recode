package com.hy0417sage.android_study.jetpack_ex.livedata_ex

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hy0417sage.android_study.R
import com.hy0417sage.android_study.databinding.FragmentLivedataTest1Binding

class LiveData1Fragment : Fragment() {

    private val TAG = "LiveDataFragment1"

    private var _binding: FragmentLivedataTest1Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLivedataTest1Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
        _binding = null 
//        fragment 는 view 보다 오래 지속된다.(lifecycle 이 다르다)
//        그래서 프래그먼트의 onDestroyView() 메서드에서 결합 클래스 인스턴스 참조를 정리해야 한다.
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

}