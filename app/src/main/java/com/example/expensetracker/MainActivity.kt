package com.example.expensetracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.expensetracker.ui.AddExpense
import com.example.expensetracker.ui.DeleteExpense
import com.example.expensetracker.ui.ViewAlll
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bottomNavigationView:BottomNavigationView=findViewById(R.id.bottomNavigationView)
        val viewFragment= ViewAlll()
        val addFragment= AddExpense()
        val deleteFragment= DeleteExpense()

        setFragment(viewFragment)
        bottomNavigationView.setOnItemSelectedListener {
            try{
                when(it.itemId){
                    R.id.viewAll-> {
                        setFragment(viewFragment)
                        true
                    }
                    R.id.delete->{
                        setFragment(deleteFragment)
                        true
                    }
                    else->{
                        R.id.add
                            setFragment(addFragment)
                            true
                    }
                }
            }catch (e:Exception){
                throw e
            }
        }


    }

    private fun setFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().setCustomAnimations( R.anim.fade_in,  // Enter animation
            R.anim.fade_out, // Exit animation
            R.anim.fade_in,  // Pop enter
            R.anim.fade_out).apply {
            replace(R.id.f1fragment,fragment).commit()
        }
    }
}