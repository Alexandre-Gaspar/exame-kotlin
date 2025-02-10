package com.alex3g.moneyflow.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun retrofitService(): ApiService {
    return Retrofit.Builder()
        .baseUrl("https://api-moneyflow.up.railway.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}