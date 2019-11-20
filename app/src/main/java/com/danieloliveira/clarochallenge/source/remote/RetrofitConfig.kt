package com.danieloliveira.clarochallenge.source.remote

import com.danieloliveira.clarochallenge.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

    private val client = OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
        }).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BaseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun service(): MovieService = retrofit.create<MovieService>(MovieService::class.java)

}