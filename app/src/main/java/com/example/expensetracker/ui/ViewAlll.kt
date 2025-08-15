package com.example.expensetracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.adapter.MyAdapter
import com.example.expensetracker.repository.Repository
import com.example.expensetracker.viewmodel.ExpenseViewModel
import com.example.expensetracker.viewmodel.ExpenseViewModelFactory

class ViewAlll : Fragment() {

    lateinit var viewModel: ExpenseViewModel
    private lateinit var adapter: MyAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_view_alll, container, false)

//        val recyclerView = view.findViewById<RecyclerView>(R.id.viewRecycleView)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        adapter = MyAdapter(mutableListOf())
//        recyclerView.adapter = adapter
////        Log.i("Information","Before viewmodel call")
//
////        viewModel = ViewModelProvider(this, ExpenseViewModelFactory(Repository()))
////            .get(ExpenseViewModel::class.java)
////        val username:String="Anubhav17"
////        viewModel.getAllExpenses(username)
////        viewModel.expenseList.observe(viewLifecycleOwner) { expenses ->
////            // update RecyclerView adapter here
////            adapter.updateData(expenses)
//////            Log.i("VALUE FORM API","${expenses[1]}")
//////            println("${expenses[1]}")
////
////        }
////
////        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
////            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
////        }
//
////        Log.i("Information","AfterS viewmodel call")

        return view
    }



}