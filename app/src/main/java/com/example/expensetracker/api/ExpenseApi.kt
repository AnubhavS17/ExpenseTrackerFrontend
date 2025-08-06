package com.example.expensetracker.api

import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.Entity.ExpenseRespose
import com.example.expensetracker.Entity.PostExpense
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ExpenseApi {

    @GET("get")
     suspend fun allExpenses(): List<Expense>

     @POST("add")
     suspend fun addExpense(@Body expense: PostExpense):Response<ExpenseRespose>

}