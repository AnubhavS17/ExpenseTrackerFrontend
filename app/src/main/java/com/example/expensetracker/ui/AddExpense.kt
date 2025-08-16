package com.example.expensetracker.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.expensetracker.Entity.PostExpense
import com.example.expensetracker.Entity.getAccessToken
import com.example.expensetracker.Entity.getAuthData
import com.example.expensetracker.Entity.getUsername
import com.example.expensetracker.R
import com.example.expensetracker.R.*
import com.example.expensetracker.repository.Repository
import com.example.expensetracker.viewmodel.ExpenseViewModel
import com.example.expensetracker.viewmodel.ExpenseViewModelFactory
import kotlinx.coroutines.launch


class AddExpense : Fragment() {

//    lateinit var addTEV:TextView
    lateinit var viewModel: ExpenseViewModel
    lateinit var nameET:EditText
    lateinit var amountET:EditText
    lateinit var categoryET:EditText
    lateinit var submitBtn:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(layout.fragment_add_expense, container, false)

        nameET=view.findViewById(R.id.etExpenseName)
        amountET=view.findViewById(R.id.etExpenseAmount)
        categoryET=view.findViewById(R.id.etExpenseCategory)
        submitBtn=view.findViewById(R.id.btnAddExpense)

        viewModel = ViewModelProvider(this, ExpenseViewModelFactory(Repository()))
            .get(ExpenseViewModel::class.java)

        submitBtn.setOnClickListener {
            val name=nameET.text.toString()
            val amount=amountET.text.toString()
            val category=categoryET.text.toString()
            if (name.isNotEmpty() && category.isNotEmpty() && amount.isNotEmpty()) {
                lifecycleScope.launch {
                    val (accessToken, _, username) = requireContext().getAuthData()
                    if (username != null && accessToken != null) {
                        val amountFinal = amount.toDoubleOrNull() ?: 0.0
                        val newExpense = PostExpense(name, amountFinal, category)
                        Log.i("IN ADDING DATA METHOD","ADD DATA")
                        Log.i("USERNAME",username)
                        Log.i("ACCESS TOKEN",accessToken)
                        viewModel.addExpense("Bearer $accessToken", username, newExpense)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Missing username or token",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }else{
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }



//        addTEV=view.findViewById(R.id.addTV)

//
//         viewModel.addExpense()
//
        viewModel.responseMessage.observe(viewLifecycleOwner){message ->
            Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
            if (message.contains("Added successfully!", ignoreCase = true)) {
                nameET.text.clear()
                categoryET.text.clear()
                amountET.text.clear()
            }
        }





        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
        return view
    }





}