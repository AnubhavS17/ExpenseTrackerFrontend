package com.example.expensetracker.repository

import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.Entity.ExpenseRespose
import com.example.expensetracker.Entity.LoginData
import com.example.expensetracker.Entity.PostExpense
import com.example.expensetracker.api.ExpenseApi
import com.example.expensetracker.api.RetroFitClient
import com.example.expensetracker.api.RetroFitLoginClient
import com.example.expensetracker.response.TokenResponse
import com.example.expensetracker.response.deleteRespose
import retrofit2.Response

class Repository() {

     suspend fun getExpenses(token:String,username:String):Response<List<Expense>>{
          return RetroFitClient.retrofitInstance.allExpenses(token,username)
     }

     suspend fun addExpense(token:String,username:String,newExpense: PostExpense):Response<ExpenseRespose>{
          return RetroFitClient.retrofitInstance.addExpense(token,username,newExpense)
     }
     suspend fun deleteExpense(token:String,username: String,name:String):Response<deleteRespose>{
          return RetroFitClient.retrofitInstance.deleteExpense(token,username,name)
     }

     suspend fun loginUser(loginData: LoginData):Response<TokenResponse>{
          return RetroFitLoginClient.retrofitLoginInstance.loginUser(loginData)
     }
}