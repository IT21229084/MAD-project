package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.user.R

class Amount : AppCompatActivity() {

    private lateinit var amount : EditText
    private lateinit var confirm : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amount)

        amount = findViewById(R.id.confirm_amount) as EditText
        confirm = findViewById(R.id.confirm_btn) as Button

        confirm.setOnClickListener {
            if(amount.text.toString()!=""){
                val i = Intent(this, add_payment::class.java)
                i.putExtra("amount", amount.text.toString())
                startActivity(i)
            }else{
                Toast.makeText(this, "Amount Required!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}