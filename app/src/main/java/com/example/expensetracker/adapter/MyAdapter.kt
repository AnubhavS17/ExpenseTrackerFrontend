package com.example.expensetracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.Entity.Expense
import com.example.expensetracker.R

class MyAdapter (private val expenseList:List<Expense>):
RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expense_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val expenseItem = expenseList[position]
        holder.paymentName.text=expenseItem.name
        holder.amount.text=expenseItem.amount.toString()
        holder.categoryName.text=expenseItem.category



    }
    override fun getItemCount(): Int {
        return expenseList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val paymentName: TextView = itemView.findViewById(R.id.paymentTV)
        val amount: TextView = itemView.findViewById(R.id.amountTV)
        val categoryName: TextView = itemView.findViewById(R.id.categoryTV)
    }

}