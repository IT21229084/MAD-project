package com.example.user.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.user.Model.Payment_all
import com.google.firebase.database.*
import com.example.user.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class All_Payments : AppCompatActivity() {
    
    private lateinit var db : DatabaseReference
    private lateinit var paymentRecyclerview : RecyclerView
    private lateinit var paymentArray : ArrayList<Payment_all>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_payments)

        paymentRecyclerview = findViewById(R.id.recyclerview)
        paymentRecyclerview.layoutManager = LinearLayoutManager(this)
        paymentRecyclerview.setHasFixedSize(true)

        paymentArray = arrayListOf<Payment_all>()
        
        load_data()
        
    }

    private fun load_data() {
        db = FirebaseDatabase.getInstance().getReference("payments")

        db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (paymentSnapshot in snapshot.children){

                        val payment = Payment_all(
                            paymentSnapshot.key.toString(),
                            paymentSnapshot.child("name").getValue(String::class.java).toString(),
                            paymentSnapshot.child("card").getValue(String::class.java).toString(),
                            paymentSnapshot.child("reason").getValue(String::class.java).toString(),
                            paymentSnapshot.child("ccv").getValue(Int::class.java)!!.toInt(),
                            paymentSnapshot.child("type").getValue(String::class.java).toString(),
                            paymentSnapshot.child("month").getValue(String::class.java).toString(),
                            paymentSnapshot.child("year").getValue(String::class.java).toString(),
                            paymentSnapshot.child("amount").getValue(Double::class.java)!!.toDouble()
                        )
                        paymentArray.add(payment!!)

                    }

                    paymentRecyclerview.adapter = paymentAdapter(paymentArray)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
    
}