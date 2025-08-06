package com.example.expensetracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.R
import com.example.expensetracker.databinding.ExpenseLayoutBinding


class MyAdapter(private var expense:List<Expense>):RecyclerView.Adapter<MyAdapter.MainViewHolder>(){
    class MainViewHolder(val binding: ExpenseLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ExpenseLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val expense=expense[position]
        holder.binding.paymentTV.text=expense.name
        holder.binding.amountTV.text=expense.amount.toString()
        holder.binding.categoryTV.text=expense.category
    }

    override fun getItemCount(): Int {
        return expense.size
    }

    fun updateData(newList: List<Expense>) {
        expense = newList
        notifyDataSetChanged()
    }

}
