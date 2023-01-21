package com.hy0417sage.android_study.jetpack_ex.binding_ex.viewbinding_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hy0417sage.android_study.databinding.ActivityViewbindingTestBinding

class ViewBingingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewbindingTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ViewBinding Test!!
        binding = ActivityViewbindingTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textView.text = "Activity ViewBinding 입니다"
    }
}