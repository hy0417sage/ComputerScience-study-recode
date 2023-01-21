package com.hy0417sage.android_study.jetpack_ex.viewmodel_ex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val num: Int) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)

        if (modelClass.isAssignableFrom(ViewModelTest::class.java)){
            return ViewModelTest(num) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}