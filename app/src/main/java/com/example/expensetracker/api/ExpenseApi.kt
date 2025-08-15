package com.example.expensetracker.api

import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.Entity.ExpenseRespose
import com.example.expensetracker.Entity.PostExpense
import com.example.expensetracker.response.deleteRespose
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ExpenseApi {

    @GET("get/{username}")
     suspend fun allExpenses(@Path("username") username:String): Response<List<Expense>>

     @POST("add/{username}")
     suspend fun addExpense(@Path("username") username: String,@Body expense: PostExpense):Response<ExpenseRespose>

     @DELETE("delete/{name}")
     suspend fun deleteExpense(@Path("name")name:String):Response<deleteRespose>
}