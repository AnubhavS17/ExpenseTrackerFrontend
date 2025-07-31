package com.example.expensetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.adapter.MyAdapter
import com.example.expensetracker.viewmodel.ExpenseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ViewAlll : Fragment() {

    lateinit var viewModel:ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_view_alll, container, false)



        val recyclerView: RecyclerView =view.findViewById(R.id.viewRecycleView)

        val expenseList:MutableList<Expense> = ArrayList()





        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        val adapter= MyAdapter(expenseList)
        recyclerView.adapter=adapter

        return view
    }

}