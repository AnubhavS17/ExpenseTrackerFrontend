package com.example.expensetracker.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.Entity.ExpenseRespose
import com.example.expensetracker.Entity.LoginData
import com.example.expensetracker.Entity.PostExpense
import com.example.expensetracker.api.RetroFitClient
import com.example.expensetracker.repository.Repository
import com.example.expensetracker.response.deleteRespose
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ExpenseViewModel(private val repository: Repository): ViewModel() {

    val expenseList=MutableLiveData<List<Expense>>()
    val errorMessage=MutableLiveData<String>()
    val username=MutableLiveData<String>()

    fun getAllExpenses(username:String){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getExpenses(username)
//                    RetroFitClient.retrofitInstance.allExpenses()
                }
                if(response.isSuccessful){
                    val body=response.body()
                    if(body != null){
                        expenseList.postValue(body?:null)
                    }
                }

            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }
        }
    }


//    val newExpense=PostExpense("TEST POST EXPENSE",0.00,"TEST2")
    val responseMessage=MutableLiveData<String>()
    fun addExpense(username: String,newExpense:PostExpense){
        viewModelScope.launch {
            try{
                val response= withContext(Dispatchers.IO){
                    repository.addExpense(username,newExpense)
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


    val deleteResponse=MutableLiveData<String>()
    fun deleteExpense(name:String){

        viewModelScope.launch {
            try{
                val response= withContext(Dispatchers.IO){
                    repository.deleteExpense(name)
                }
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null) {
                        deleteResponse.postValue(body.message?: "Added successfully!")
                    } else {
                        errorMessage.postValue("Empty response from server")
                    }
                }
                else{
                    errorMessage.postValue("Error: ${response.code()} ${response.message()}")
                }

            }catch (e:Exception){
                errorMessage.postValue(e.message)
            }
        }
    }





    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean>get() = _loginState
    fun loginUser(loginData: LoginData){
        Log.i("INSIDE VIEW MODEL","LOGIN USER OUTSIDE TRY BLOCK")
        viewModelScope.launch {
            try{
                Log.i("INSIDE VIEW MODEL","TRY BLOCK")
                Log.i("INSIDE VIEW MODEL","LOGIN DATA ${loginData.username}")
                Log.i("INSIDE VIEW MODEL","LOGIN DATA ${loginData.password}")
                val response= withContext(Dispatchers.IO){
                    repository.loginUser(loginData)
                }
                Log.i("RESPONSE","${response}")
                Log.i("RESPONSE","${response.body()}")
                Log.i("RESPONSE","${response.errorBody()}")
                if(response.isSuccessful && response.body()?.accessToken != null) {
                    val body = response.body()
                    Log.i("IN ACCESS TOKEN BLOCK", "ACCESS TOKEN")
                    Log.i("INSIDE VIEW MODEL", "${body}")
                    if (body != null) {
                        println("AccessToken: ${body.accessToken}")
                        println("RefreshToken: ${body.token}")
                    } else {
                        println(response.message())
                    }
                    _loginState.postValue(true)
                }
                else{
                    Log.i("IN else block of error","error")
                    println("Error: ${response.code()} - ${response.errorBody()?.string()}")
                    _loginState.postValue(false)
                }
        }catch (e:Exception){
            Log.i("INSIDE VIEW MODEL CATCH BLOCK","${e.message}")
            println("Exception ${e.message}")
                _loginState.postValue(false)
            }
        }
    }



}


