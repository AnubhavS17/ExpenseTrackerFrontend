package com.example.expensetracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.api.RetroFitClient
import com.example.expensetracker.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ExpenseViewModel(private val repository: Repository): ViewModel() {

    val expenseList=MutableLiveData<List<Expense>>()
    val errorMessage=MutableLiveData<String>()

    fun getAllExpenses(){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getExpenses()
//                    RetroFitClient.retrofitInstance.allExpenses()
                }
                expenseList.postValue(response)
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }
        }
    }
}


