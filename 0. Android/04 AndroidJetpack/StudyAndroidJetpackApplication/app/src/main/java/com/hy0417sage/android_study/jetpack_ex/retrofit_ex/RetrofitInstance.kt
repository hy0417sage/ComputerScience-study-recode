package com.hy0417sage.android_study.jetpack_ex.retrofit_ex

import retrofit2.Retrofit

object RetrofitInstance {

    val BASE_URL = "https://jsonplaceholder.typicode.com/" //기반 URL

    val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance() = client
}