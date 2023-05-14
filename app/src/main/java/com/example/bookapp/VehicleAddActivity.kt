package com.example.bookapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookapp.databinding.ActivityVehicleAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class VehicleAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVehicleAddBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(false)

        //back btn
        binding.backBtn.setOnClickListener{
            onBackPressed()
        }

        //click submit
        binding.submitVehicleBtn.setOnClickListener{
            validateData()
        }
    }

    private var vehicleName = ""
    private var vehicleDescription = ""
    private var vehicleCount = ""
    private var vehicleType = ""
    private var shop = ""


    private fun validateData() {
        vehicleName = binding.vehicleNameEt.text.toString().trim()
        vehicleDescription = binding.vehicleDescriptionEt.text.toString().trim()
        vehicleCount = binding.vehicleCountEt.text.toString().trim()
        vehicleType = binding.vehicleTypeEt.text.toString().trim()
        shop = binding.vehicleShopEt.text.toString().trim()

        if (vehicleName.isEmpty() || vehicleType.isEmpty() || vehicleDescription.isEmpty()){
            Toast.makeText(this, "Fill required fields", Toast.LENGTH_SHORT).show()
        }
        else{
            addVehicleFirebase()
        }
    }

    private fun addVehicleFirebase() {
        progressDialog.show()

        val timestamp = System.currentTimeMillis()

        val hashMap = HashMap<String, Any>()
        hashMap["vehicleID"] = timestamp.toString()
        hashMap["vehicleName"] = vehicleName
        hashMap["vehicleDescription"] = vehicleDescription
        hashMap["vehicleCount"] = vehicleCount
        hashMap["vehicleType"] = vehicleType
        hashMap["shop"] = shop


        //add to DB
        val ref = FirebaseDatabase.getInstance().getReference("Vehicles")
        ref.child("$timestamp")
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this, "Unsuccessful ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}