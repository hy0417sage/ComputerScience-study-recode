package com.hy0417sage.android_study.jetpack_ex.retrofit_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hy0417sage.android_study.databinding.ActivityRetrofitBinding
import retrofit2.Call
import retrofit2.Callback

class RetrofitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetrofitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val api = RetrofitInstance.getInstance().create(MyAPI::class.java)


        api.getPost1().enqueue(object : Callback<Post> {
            override fun onRespone(callback: Call<Post>, response: Respones<Post>){

            }
            override fun onFailure(callback: Call<Post>, t: Throwable){

            }
        })


        api.getPostNumber(2).enqueue(object : Callback<Post>{
            override fun onRespone(callback: Call<Post>, response: Respones<Post>){

            }
            override fun onFailure(callback: Call<Post>, t: Throwable){

            }
        })
    }
}