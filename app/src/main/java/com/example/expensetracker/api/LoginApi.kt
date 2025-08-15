package com.example.expensetracker.api

import com.example.expensetracker.Entity.LoginData
import com.example.expensetracker.response.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("auth/v1/login")
    suspend fun loginUser(@Body loginInfo:LoginData):Response<TokenResponse>
}