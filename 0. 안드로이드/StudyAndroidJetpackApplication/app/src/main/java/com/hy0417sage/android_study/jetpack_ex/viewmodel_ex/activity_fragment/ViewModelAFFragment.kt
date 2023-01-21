//package com.hy0417sage.android_study.jetpack_ex.viewmodel_ex.activity_fragment
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
//import com.hy0417sage.android_study.R
//import com.hy0417sage.android_study.databinding.FragmentViewmodelAfTestBinding
//
//class ViewModelAFFragment : Fragment() {
//
//    private lateinit var binding: FragmentViewmodelAfTestBinding
//    private val viewModel: AFViewModel by activityViewModels() //activity 와 연결
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_viewmodel_af_test, container, false)
//        binding.fragmentText.text = viewModel.getCount().toString()
//
//        return binding.root
//    }
//}