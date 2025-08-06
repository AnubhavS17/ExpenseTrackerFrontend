package com.example.expensetracker.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.Entity.ExpenseRespose
import com.example.expensetracker.Entity.PostExpense
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


//    val newExpense=PostExpense("TEST POST EXPENSE",0.00,"TEST2")
    val responseMessage=MutableLiveData<String>()
    fun addExpense(newExpense:PostExpense){
        viewModelScope.launch {
            try{
                val response= withContext(Dispatchers.IO){
                    repository.addExpense(newExpense)
                }
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        // Safely handle message
                        responseMessage.postValue(body.message ?: "Added successfully!")
                    } else {
                        errorMessage.postValue("Empty response from server")
                    }
                } else {
                    errorMessage.postValue("Error: ${response.code()} ${response.message()}")
                }
            }catch (e:Exception){
                errorMessage.postValue(e.message)
            }
        }
    }
}


