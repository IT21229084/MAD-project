package com.example.bookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bookapp.databinding.ActivityVehicleListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VehicleListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVehicleListBinding

    private companion object{
        const val TAG = "VEHICLE_LIST_TAG"
    }

    private var shopID = 0
    private var shopName = ""

    private lateinit var vehicleList:ArrayList<ModelVehicle>
    private lateinit var adapterVehicle: AdapterVehicle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleListBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_vehicle_list)

        //from adapter
        val intent = intent
        shopID = intent.getIntExtra("shopID", 0)!!
        shopName = intent.getStringExtra("shopName")!!

        binding.subTitleTv.text = shopName

        loadVehicleList()
    }

    private fun loadVehicleList() {
        vehicleList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("Vehicles")
        ref.orderByChild("shopID").equalTo(shopID.toString())
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    vehicleList.clear()
                    for (ds in snapshot.children){
                        val model = ds.getValue(ModelVehicle::class.java)
                        if (model != null){
                            vehicleList.add(model)
                            Log.d(TAG, "onDataChange: ${model.vehicleName} ${model.shopID}")
                        }
                    }
                    adapterVehicle = AdapterVehicle(this@VehicleListActivity, vehicleList)
                    binding.vehiclesRv.adapter = adapterVehicle
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }
}