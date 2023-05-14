package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.user.R

// Class definition for the activity to register either as a customer or a business
class RegisterDir : AppCompatActivity() {
    //Declare Variable
    private lateinit var cusbtn:Button
    private lateinit var bussBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_dir)

        // Assign the buttons to their respective UI elements
        cusbtn = findViewById(R.id.cus)
        bussBtn = findViewById(R.id.bus)

        // Set the onClickListeners for the buttons
        cusbtn.setOnClickListener {
            intent = Intent(applicationContext, Register::class.java)
            startActivity(intent)
        }
        bussBtn.setOnClickListener {
            intent = Intent(applicationContext, B_Register::class.java)
            startActivity(intent)
        }
    }
}