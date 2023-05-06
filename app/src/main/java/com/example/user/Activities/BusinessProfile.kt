package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.user.Model.UserModel
import com.example.user.R
import com.example.user.databinding.ActivityBusinessProfileBinding
import com.example.user.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class BusinessProfile : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var dbRef: DatabaseReference
    private lateinit var binding:ActivityBusinessProfileBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user : UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("user")


        databaseReference.child(firebaseAuth.currentUser!!.uid).addValueEventListener(object :
            ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                //retrieve values from the db and convert them to user data class
                user = snapshot.getValue(UserModel::class.java)!!

                binding.userNamebs.text = user.userName
                binding.Emailbs.text = user.Email
                binding.MobileNobs.text = user.MobileNo
                binding.NICbs.text = user.NIC
                binding.addrbs.text = user.Address
                binding.passwdbs.text = user.password

            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        binding.Eprofilebs.setOnClickListener {
            intent = Intent(applicationContext, editProfile::class.java).also {
                it.putExtra("userName", user.userName)
                it.putExtra("email", user.Email)
                it.putExtra("mobileNo", user.MobileNo)
                it.putExtra("address", user.Address)
                it.putExtra("nic", user.NIC)
                it.putExtra("password", user.password)

                startActivity(it)
            }


        }
    }


}