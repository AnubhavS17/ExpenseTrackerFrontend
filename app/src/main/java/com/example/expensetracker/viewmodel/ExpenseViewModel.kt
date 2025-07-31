package com.example.expensetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.repository.Repository
import retrofit2.Response


class ExpenseViewModel(private val repository: Repository,private val application: Application):AndroidViewModel(application) {

    val expenseLiveData:MutableLiveData<List<Expense>> = MutableLiveData()

//    fun allExpenses()=viewModelScope.launch{
//
//    }

}


