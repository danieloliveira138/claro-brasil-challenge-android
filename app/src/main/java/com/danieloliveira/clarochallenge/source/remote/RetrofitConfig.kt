package com.danieloliveira.clarochallenge.source.remote

import com.danieloliveira.clarochallenge.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun service(): MovieService = retrofit.create<MovieService>(MovieService::class.java)

}