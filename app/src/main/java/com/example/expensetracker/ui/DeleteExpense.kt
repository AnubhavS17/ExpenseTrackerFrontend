package com.example.expensetracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.adapter.MyAdapter
import com.example.expensetracker.repository.Repository
import com.example.expensetracker.viewmodel.ExpenseViewModel
import com.example.expensetracker.viewmodel.ExpenseViewModelFactory


class DeleteExpense : Fragment() {

    lateinit var viewModel: ExpenseViewModel
    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_delete_expense, container, false)

//        val recyclerView = view.findViewById<RecyclerView>(R.id.deleteRV)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        adapter = MyAdapter(mutableListOf())
//        recyclerView.adapter = adapter
////        Log.i("Information","Before viewmodel call")
//
//        //initialising view model
////        viewModel = ViewModelProvider(this, ExpenseViewModelFactory(Repository()))
////            .get(ExpenseViewModel::class.java)
////
////        val username:String="Anubhav17"
////        //calling API
////        viewModel.getAllExpenses(username)
////        viewModel.expenseList.observe(viewLifecycleOwner) { expenses ->
////            // update RecyclerView adapter here
////            adapter.updateData(expenses)
//////            Log.i("VALUE FORM API","${expenses[1]}")
////        }
//
//        //for errors
//        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
//            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
//        }
//
//
//
////        val name:String="WATER BILL"
////        viewModel.deleteExpense(name)
////        viewModel.deleteResponse.observe(viewLifecycleOwner){delete->
////            Toast.makeText(requireContext(),delete,Toast.LENGTH_SHORT).show()
////        }
//        enableSwipeToDelete(recyclerView, adapter)


        return view
    }

    private fun enableSwipeToDelete(recyclerView: RecyclerView, adapter: MyAdapter) {
        val swipeHandler = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                //getting that item
                val expenseItem = adapter.getExpenseAt(position)

                //removing that item
                adapter.removeAt(position)

                //api call
                viewModel.deleteExpense(expenseItem.name)
                viewModel.deleteResponse.observe(viewLifecycleOwner) { response ->
                    Toast.makeText(requireContext(), response, Toast.LENGTH_SHORT).show()
                }
            }
        }

        ItemTouchHelper(swipeHandler).attachToRecyclerView(recyclerView)
    }

}