package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.user.Model.UserModel
import com.example.user.R
import com.example.user.databinding.ActivityBusinessProfileBinding
import com.example.user.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class BusinessProfile : AppCompatActivity() {

    // Declare variables for FirebaseAuth, DatabaseReference, ActivityProfileBinding, and UserModel
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var dbRef: DatabaseReference
    private lateinit var binding:ActivityBusinessProfileBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user : UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the current user's FirebaseAuth instance and initialize the DatabaseReference
        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("user")

        // Set an OnClickListener for the edit  profile button to start the editBusinessProfile activity
        databaseReference.child(firebaseAuth.currentUser!!.uid).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                //retrieve values from the db and convert them to user data class
                user = snapshot.getValue(UserModel::class.java)!!

                // Set the user data to the corresponding TextViews in the layout
                binding.userNamebs.text = user.userName
                binding.Emailbs.text = user.Email
                binding.MobileNobs.text = user.MobileNo
                binding.NICbs.text = user.NIC
                binding.addrbs.text = user.Address
                binding.passwdbs.text = user.password

            }
            override fun onCancelled(error: DatabaseError) {}
        })

        // Set an OnClickListener for the edit profile button to start the editBusinessProfile activity
        binding.Eprofilebs.setOnClickListener {
            intent = Intent(applicationContext, EditBusineesProfile::class.java).also {
                it.putExtra("userName", user.userName)
                it.putExtra("email", user.Email)
                it.putExtra("mobileNo", user.MobileNo)
                it.putExtra("address", user.Address)
                it.putExtra("nic", user.NIC)
                it.putExtra("password", user.password)

                startActivity(it)
            }
        }
        // Set an OnClickListener for the edit profile button to start the Login activity
        binding.logoutbs.setOnClickListener {
            Firebase.auth.signOut()

            //redirect user to login page
            intent = Intent(applicationContext, LogIn::class.java)
            startActivity(intent)

            //toast message
            Toast.makeText(this, "Successfully logged out", Toast.LENGTH_SHORT).show()
        }
    }
}