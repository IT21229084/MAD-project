package com.example.bookapp

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bookapp.databinding.ActivityVehicleEditBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VehicleEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVehicleEditBinding

    private var vehicleID = ""
    private lateinit var progressDialog: ProgressDialog

//    private var vehicleTitleArrayList: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get vID to update
        vehicleID = intent.getStringExtra("vehicleID")!!

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(false)

        loadVehicleInfo()

        binding.backBtn.setOnClickListener{
            onBackPressed()
        }

        //update click
        binding.submitVehicleBtn1.setOnClickListener{
            validateData()
        }
    }

    private fun loadVehicleInfo() {
        Log.d(TAG, "loadVehicleInfo: Loading vehicle details")

        val ref = FirebaseDatabase.getInstance().getReference("Vehicles")
        ref.child(vehicleID)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val vehicleName = snapshot.child("vehicleName").value.toString()
                    val vehicleDescription = snapshot.child("vehicleDescription").value.toString()
                    val vehicleCount = snapshot.child("vehicleCount").value.toString()

                    //bind to fields
                    binding.vehicleNameEt1.setText(vehicleName)
                    binding.vehicleDescriptionEt1.setText(vehicleDescription)
                    binding.vehicleCountEt1.setText(vehicleCount)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    //    private var vID = ""
    private var vehicleName = ""
    private var vehicleDescription = ""
    private var vehicleCount = ""


    private fun validateData() {
        vehicleName = binding.vehicleNameEt1.text.toString().trim()
        vehicleDescription = binding.vehicleDescriptionEt1.text.toString().trim()
        vehicleCount = binding.vehicleCountEt1.text.toString().trim()

        //validate
        if (vehicleName.isEmpty() || vehicleDescription.isEmpty() || vehicleCount.isEmpty()){
            Toast.makeText(this, "Fill required fields", Toast.LENGTH_SHORT).show()
        }
        else{
            updateVehicle()
        }
    }

    private fun updateVehicle() {
        Log.d(TAG, "updateVehicle: ")

        progressDialog.setMessage("Updating Vehicle")
        progressDialog.show()

        //update data
        val hashMap = HashMap<String, Any>()
        hashMap["vehicleName"] = "$vehicleName"
        hashMap["vehicleDescription"] = "$vehicleDescription"
        hashMap["vehicleCount"] = "$vehicleCount"

        val ref = FirebaseDatabase.getInstance().getReference("Vehicles")
        ref.child(vehicleID)
            .updateChildren(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Log.d(TAG, "updateVehicle: Updated Successfully")
                Toast.makeText(this,"Updated Successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.d(TAG, "updateVehicle: Update Fail ${e.message}")
                progressDialog.dismiss()
                Toast.makeText( this, "Update Fail ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}