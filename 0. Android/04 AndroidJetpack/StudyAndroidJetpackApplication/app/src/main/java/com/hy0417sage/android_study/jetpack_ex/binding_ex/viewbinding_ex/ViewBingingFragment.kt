package com.hy0417sage.android_study.jetpack_ex.binding_ex.viewbinding_ex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hy0417sage.android_study.databinding.FragmentViewbindingTestBinding

class ViewBingingFragment : Fragment() {

    private var _binding: FragmentViewbindingTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentViewbindingTestBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.fragmentText.text = "Fragment ViewBinging 입니다"

        return view
    }
}