package com.example.bookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.bookapp.databinding.ActivityVehicleListAdminBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VehicleListAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVehicleListAdminBinding

    private lateinit var vehicleAdminArrayList: ArrayList<ModelVehicleAdmin>
    private lateinit var adapterVehicleAdmin: AdapterVehicleAdmin


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleListAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadVehiclesAdmin()

        //click add vehicle
        binding.addVehicleBtn.setOnClickListener {
            startActivity(Intent(this, VehicleAddActivity::class.java))
        }
    }

    private fun loadVehiclesAdmin() {
        vehicleAdminArrayList = ArrayList()

        val ref = FirebaseDatabase.getInstance().getReference("Vehicles")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                vehicleAdminArrayList.clear()
                for (ds in snapshot.children){
                    val model = ds.getValue(ModelVehicleAdmin::class.java)
                    vehicleAdminArrayList.add(model!!)
                }
                adapterVehicleAdmin = AdapterVehicleAdmin(this@VehicleListAdminActivity, vehicleAdminArrayList)
                binding.vehiclesRv.adapter = adapterVehicleAdmin
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


}