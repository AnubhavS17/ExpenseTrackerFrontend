package com.example.expensetracker.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitLoginClient {

    private const val base_url="http://192.168.0.106:8081/"

    val retrofitLoginInstance:LoginApi by lazy{
        Retrofit.Builder().baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(LoginApi::class.java)
    }
}