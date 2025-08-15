package com.example.expensetracker.ui

import android.content.Intent
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.example.expensetracker.Entity.LoginData
import com.example.expensetracker.MainActivity
import com.example.expensetracker.R
import com.example.expensetracker.databinding.ActivityLoginBinding
import com.example.expensetracker.repository.Repository
import com.example.expensetracker.viewmodel.ExpenseViewModel
import com.example.expensetracker.viewmodel.ExpenseViewModelFactory

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

        // Observe once, outside click listener
        viewModel.loginState.observe(this) { success ->
            if (success) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
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

            val loginInfo = LoginData(username, password)
            viewModel.loginUser(loginInfo)
//

        }
    }
}