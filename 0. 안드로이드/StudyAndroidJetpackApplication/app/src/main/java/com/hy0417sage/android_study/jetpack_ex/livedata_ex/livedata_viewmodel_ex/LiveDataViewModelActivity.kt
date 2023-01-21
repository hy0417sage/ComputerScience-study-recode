package com.hy0417sage.android_study.jetpack_ex.livedata_ex.livedata_viewmodel_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hy0417sage.android_study.databinding.ActivityLivedataViewmodelTestBinding

class LiveDataViewModelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLivedataViewmodelTestBinding
    private lateinit var viewModel: LiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLivedataViewmodelTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(LiveDataViewModel::class.java)
        binding.button.setOnClickListener {
            viewModel.plusLiveDataValue()
        }

        viewModel.testLiveData.observe(this, Observer {
            Log.d("testMutableLiveData", it.toString())
        })
    }
}