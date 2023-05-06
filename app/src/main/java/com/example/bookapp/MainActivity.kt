package com.example.bookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //click continue
        binding.continueBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        //click vehicle list
        binding.vehicleListAdminBtn.setOnClickListener {
            startActivity(Intent(this, VehicleListAdminActivity::class.java))
        }
    }
}