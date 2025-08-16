package com.example.expensetracker.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.Entity.AuthResponse
import com.example.expensetracker.Entity.dataStore
import com.example.expensetracker.R
import com.example.expensetracker.adapter.MyAdapter
import com.example.expensetracker.repository.Repository
import com.example.expensetracker.viewmodel.ExpenseViewModel
import com.example.expensetracker.viewmodel.ExpenseViewModelFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ViewAlll : Fragment() {

    lateinit var viewModel: ExpenseViewModel
    private lateinit var adapter: MyAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_view_alll, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.viewRecycleView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyAdapter(mutableListOf())
        recyclerView.adapter = adapter
        Log.i("Information","Before viewmodel call")

        viewModel = ViewModelProvider(this, ExpenseViewModelFactory(Repository()))
            .get(ExpenseViewModel::class.java)

//        val username ="TEST4"
//        if (username != null) {
//            viewModel.getAllExpenses(username)
//        }


        viewLifecycleOwner.lifecycleScope.launch {
            val prefs = requireContext().dataStore.data.first()

            val accessToken = prefs[AuthResponse.ACCESS_TOKEN] ?: ""
            val username = prefs[AuthResponse.USER_NAME] ?: ""

            Log.i("INTO THE VIEW ALL METHOD","GETTING DATA")
            Log.i("Access Token", accessToken)
            Log.i("Username", username)

            //  ViewModel call
            viewModel.getAllExpenses("Bearer $accessToken", username)
        }

        viewModel.expenseList.observe(viewLifecycleOwner) { expenses ->
            // update RecyclerView adapter here
            adapter.updateData(expenses)
//            Log.i("VALUE FORM API","${expenses[1]}")
//            println("${expenses[1]}")

        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        Log.i("Information","AfterS viewmodel call")

        return view
    }



}