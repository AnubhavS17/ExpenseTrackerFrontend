package com.example.expensetracker.repository

import com.example.expensetracker.api.RetroFitClient

class Repository {

    suspend fun getExpenses()=RetroFitClient.retrofitInstance.allExpenses()
}