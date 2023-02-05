package com.hy0417sage.android_study.jetpack_ex.viewmodel_ex.activity_fragment

import android.util.Log
import androidx.lifecycle.ViewModel

class AFViewModel : ViewModel() {

    private val TAG = "ViewModelTest"

    private var countValue = 0
    fun plusValue() = countValue++
    fun minusValue() = countValue--

    fun getCount(): Int {
        Log.d(TAG, "ViewModelTest")
        return countValue
    }
}