package com.example.bookapp

import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
//import com.google.firebase.storage.FirebaseStorage
import java.util.*

class MyApplication:Application() {

    override fun onCreate(){
        super.onCreate()
    }

    companion object{
        fun loadShop(shopID: String, categoryTv: TextView){
            val ref = FirebaseDatabase.getInstance().getReference("Shops")
            ref.child(shopID.toString())
                .addListenerForSingleValueEvent(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        //get shop
                        val shop = "${snapshot.child("shopName").value}"

                        categoryTv.text = shop
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })
        }

        fun deleteVehicle(context: Context, vehicleID: String, vehicleName: String){
            val TAG = "DELETE_VEHICLE_TAG"

            Log.d(TAG, "deleteVehicle: Deleting")

            val progressDialog = ProgressDialog(context)
//            progressDialog.setTitle("Please Wait")
//            progressDialog.setMessage("Deleting ${vehicleName}")
//            progressDialog.setCanceledOnTouchOutside(false)
//            progressDialog.show()

            val ref = FirebaseDatabase.getInstance().getReference("Vehicles")
            ref.child(vehicleID)
                .removeValue()
                .addOnSuccessListener {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{e->
                    Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show()
                }

//            Log.d(TAG, "deleteVehicle: ")
        }
    }
}