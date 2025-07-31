package com.example.expensetracker.Entity

class Expense {

    var name:String ? =null
    var amount:Int ?=0
    var category:String ? =null

    constructor(name1:String ,amount1:Int,category1:String){
        this.name=name1
        this.amount=amount1
        this.category=category1
    }

}