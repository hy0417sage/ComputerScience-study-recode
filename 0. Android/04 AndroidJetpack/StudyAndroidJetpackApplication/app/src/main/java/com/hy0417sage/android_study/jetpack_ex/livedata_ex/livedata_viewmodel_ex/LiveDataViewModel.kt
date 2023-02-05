package com.hy0417sage.android_study.jetpack_ex.livedata_ex.livedata_viewmodel_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel : ViewModel() {

    private var testMutableLiveData = MutableLiveData(0)
    val testLiveData: LiveData<Int>
        get() = testMutableLiveData

    //MutableLiveData 는 변경 가능 / LiveData 는 읽을 수 있지만 변경할 수 없다 (캡슐화 가능)
    fun plusLiveDataValue(){
        testMutableLiveData.value = testMutableLiveData.value!!.plus(1)
    }
}