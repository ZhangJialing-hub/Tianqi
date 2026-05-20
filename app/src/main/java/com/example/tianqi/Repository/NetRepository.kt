package com.example.tianqi.Repository

/*
* description:  ToDo:网络请求
 * author:zjl
 * email:3507386031@qq.com
 * date:2026/5/17 14：41
 */

import com.example.tianqi.Model.GsonData
import com.google.firebase.appdistribution.gradle.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object NetRepository {
    private val retrofit= Retrofit.Builder()
        .baseUrl("https://api.caiyunapp.com/v2.6/TAkhjf8d1nlSlspN/101.6656,39.2072/realtime")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val apiService=retrofit.create(ApiService::class.java)
    interface ApiService{
        @GET("TAkhjf8d1nlSlspN/101.6656,39.2072/realtime")
        suspend fun getData(): GsonData
    }
}