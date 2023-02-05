package com.hy0417sage.android_study.jetpack_ex.livedata_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hy0417sage.android_study.R
import com.hy0417sage.android_study.databinding.ActivityLivedataTestBinding

class LiveDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLivedataTestBinding
    private var testMutableLiveData = MutableLiveData(0)

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLivedataTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            testMutableLiveData.value = testMutableLiveData.value!!.plus(1)
        }

        binding.fragment1.setOnClickListener {
            val transformations1 = manager.beginTransaction()
            val fragment1 = LiveData1Fragment()
            transformations1.replace(R.id.textView, fragment1)
            transformations1.addToBackStack(null)
            transformations1.commit()
        }

        binding.fragment2.setOnClickListener {
            val transformations2 = manager.beginTransaction()
            val fragment2 = LiveData2Fragment()
            transformations2.replace(R.id.textView, fragment2)
            transformations2.addToBackStack(null)
            transformations2.commit()
        }

        //testMutableLiveData 가 바뀌는 것을 관찰 : testMutableLiveData 값이 변경될 때 마다 로그가 실행된다.
        testMutableLiveData.observe(this, Observer{
            Log.d("testMutableLiveData", testMutableLiveData.value.toString())
        })
    }
}