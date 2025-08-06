package com.example.expensetracker.api

import com.example.expensetracker.Entity.Expense
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ExpenseApi {

    @GET("get")
     suspend fun allExpenses(): List<Expense>

}