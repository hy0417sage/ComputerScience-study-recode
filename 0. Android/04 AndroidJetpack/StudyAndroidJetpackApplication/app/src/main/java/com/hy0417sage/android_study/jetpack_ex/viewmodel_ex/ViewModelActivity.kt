package com.hy0417sage.android_study.jetpack_ex.viewmodel_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hy0417sage.android_study.R
import com.hy0417sage.android_study.databinding.ActivityViewbindingTestBinding
import com.hy0417sage.android_study.databinding.ActivityViewmodelTestBinding

class ViewModelActivity : AppCompatActivity() {

    private val TAG = "ViewModelActivity"
    private lateinit var binding: ActivityViewmodelTestBinding
    private var countValue = 0

    lateinit var viewModel: ViewModelTest

    val manager = supportFragmentManager //fragment 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewmodelTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val transformations = manager.beginTransaction()
        val fragment = ViewModelFragment()
        transformations.replace(R.id.frameArea, fragment)
        transformations.addToBackStack(null)
        transformations.commit()

        Log.d(TAG, "onCreate")

        viewModel = ViewModelProvider(this).get(ViewModelTest::class.java)

        //액티비티 내에서 모든걸 작성하다 보면,
        // 화면을 회전할 때 라이프사이클이 파괴되고 생성되기 때문에
        // view model 을 사용하여 별개로 처리하는것이 좋다.
        binding.plusButton.setOnClickListener {

            //뷰 모델이 아닐 때
            countValue++
            binding.textResult2.text = countValue.toString()

            //뷰 모델 일 때
            viewModel.plusValue()
            binding.textResult1.text = viewModel.getCount().toString()
        }

        binding.minusButton.setOnClickListener {

            //뷰 모델이 아닐 때
            countValue--
            binding.textResult2.text = countValue.toString()

            //뷰 모델 일 때
            viewModel.minusValue()
            binding.textResult1.text = viewModel.getCount().toString()
        }

    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}