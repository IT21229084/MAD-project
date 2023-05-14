package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.user.R

class paymentsmain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paymentsmain)

        val add = findViewById(R.id.new_payment) as Button
        val all = findViewById(R.id.list_payment) as Button

        add.setOnClickListener {
            startActivity(Intent(this, Amount::class.java))
        }

        all.setOnClickListener {
            startActivity(Intent(this, All_Payments::class.java))
        }
    }
}