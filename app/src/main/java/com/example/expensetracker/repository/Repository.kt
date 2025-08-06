package com.example.expensetracker.repository

import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.Entity.ExpenseRespose
import com.example.expensetracker.Entity.PostExpense
import com.example.expensetracker.api.ExpenseApi
import com.example.expensetracker.api.RetroFitClient
import retrofit2.Response

class Repository() {

     suspend fun getExpenses():List<Expense>{
          return RetroFitClient.retrofitInstance.allExpenses()
     }

     suspend fun addExpense(newExpense: PostExpense):Response<ExpenseRespose>{
          return RetroFitClient.retrofitInstance.addExpense(newExpense)
     }
}