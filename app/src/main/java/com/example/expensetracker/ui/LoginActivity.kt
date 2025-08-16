package com.example.expensetracker.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.expensetracker.Entity.AuthResponse
import com.example.expensetracker.Entity.LoginData
import com.example.expensetracker.Entity.dataStore
import com.example.expensetracker.Entity.saveAuthData
import com.example.expensetracker.MainActivity
import com.example.expensetracker.R
import com.example.expensetracker.databinding.ActivityLoginBinding
import com.example.expensetracker.repository.Repository
import com.example.expensetracker.viewmodel.ExpenseViewModel
import com.example.expensetracker.viewmodel.ExpenseViewModelFactory
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding
    lateinit var viewModel: ExpenseViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this, ExpenseViewModelFactory(Repository()))
            .get(ExpenseViewModel::class.java)

        val intent = Intent(this, MainActivity::class.java)


        // Observe once, outside click listener
        viewModel.loginState.observe(this) { success ->
            binding.btnLogin.isEnabled = true
            if(success) {
                val response = viewModel.loginResponse.value
                response?.let {
                    val accessToken = it.accessToken
                    val refreshToken = it.token
                    val username = binding.etUsername.text.toString()

                    Log.i("Access Token", accessToken)
                    Log.i("Refresh Token", refreshToken)
                    Log.i("Username", username)

                    lifecycleScope.launch {
                        applicationContext.saveAuthData(accessToken, refreshToken, username)
                    }
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        }

        // Click listener just checks fields and calls ViewModel
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please Enter Both Fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            binding.btnLogin.isEnabled = false
            val loginInfo = LoginData(username, password)
            viewModel.loginUser(loginInfo)

        }
    }

//     private suspend fun saveTokens(accessToken: String, refreshToken: String,username:String) {
//        applicationContext.dataStore.edit { prefs ->
//            prefs[AuthResponse.ACCESS_TOKEN] = accessToken
//            prefs[AuthResponse.REFRESH_TOKEN] = refreshToken
//            prefs[AuthResponse.USER_NAME]=username
//        }
//    }
}