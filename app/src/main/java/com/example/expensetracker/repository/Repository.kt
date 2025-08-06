package com.example.expensetracker.repository

import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.api.ExpenseApi
import com.example.expensetracker.api.RetroFitClient

class Repository() {

     suspend fun getExpenses():List<Expense>{
          return RetroFitClient.retrofitInstance.allExpenses()
     }
}