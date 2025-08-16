package com.example.expensetracker.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.Entity.ExpenseRespose
import com.example.expensetracker.Entity.LoginData
import com.example.expensetracker.Entity.LoginResponse
import com.example.expensetracker.Entity.PostExpense
import com.example.expensetracker.api.RetroFitClient
import com.example.expensetracker.repository.Repository
import com.example.expensetracker.response.TokenResponse
import com.example.expensetracker.response.deleteRespose
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.prefs.Preferences



class ExpenseViewModel(private val repository: Repository): ViewModel() {

    val expenseList=MutableLiveData<List<Expense>>()
    val errorMessage=MutableLiveData<String>()
    val username=MutableLiveData<String>()
//    var dataStore:DataStore<androidx.datastore.preferences.core.Preferences>




    fun getAllExpenses(token:String,username:String){
        viewModelScope.launch {
            try {
                    val response = withContext(Dispatchers.IO) {
                        repository.getExpenses(token,username)
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
    fun addExpense(token: String,username: String,newExpense:PostExpense){
        viewModelScope.launch {
            try{
                    val response= withContext(Dispatchers.IO){
                        repository.addExpense(token,username,newExpense)
                    }
                    Log.i("ADD EXPENSE ","API CALLED")
                    if (response.isSuccessful) {
                        Log.i("INTO SUCCESSFULL","RESPONSE")
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
                Log.i("INTO EXCEPTION","EXCEPTION")
                errorMessage.postValue(e.message)
            }
        }
    }


    val deleteResponse=MutableLiveData<String>()
    fun deleteExpense(token: String,username: String,name:String){

        viewModelScope.launch {
            try{
                    val response= withContext(Dispatchers.IO){
                        repository.deleteExpense(token,username,name)
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
    val loginResponse=MutableLiveData<TokenResponse?>()

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
                if(response.isSuccessful && response.body() != null) {
                    loginResponse.postValue(response.body())
                    _loginState.postValue(true)
                }
                else{
                    Log.i("IN else block of error","error")
                    println("Error: ${response.code()} - ${response.errorBody()?.string()}")
                    loginResponse.postValue(null)
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


