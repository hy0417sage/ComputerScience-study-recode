package com.hy0417sage.android_study.jetpack_ex.viewmodel_ex.activity_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hy0417sage.android_study.R
import com.hy0417sage.android_study.databinding.ActivityViewmodelAfTestBinding

class ViewModelAFActivity : AppCompatActivity() {

    lateinit var viewModel: AFViewModel
    lateinit var binding: ActivityViewmodelAfTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_viewmodel_af_test)
        viewModel = ViewModelProvider(this).get(AFViewModel::class.java)

        binding.plusButton.setOnClickListener {
            viewModel.plusValue()
            binding.resultArea.text = viewModel.getCount().toString()
        }

        binding.minusButton.setOnClickListener {
            viewModel.minusValue()
            binding.resultArea.text = viewModel.getCount().toString()
        }

        val manager = supportFragmentManager
        binding.showFragment.setOnClickListener {
            val transaction = manager.beginTransaction()
            val fragment = ViewModelAFFragment()
            transaction.replace(R.id.frameArea, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}