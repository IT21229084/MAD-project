package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.user.Model.UserModel
import com.example.user.R
import com.example.user.databinding.ActivityBregisterBinding
import com.example.user.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class B_Register : AppCompatActivity() {

    // Declare variables
    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: ActivityBregisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    // This method is called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout for the activity
        binding = ActivityBregisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get an instance of FirebaseAuth and FirebaseDatabase
        firebaseAuth = FirebaseAuth.getInstance()
        val auth = Firebase.auth
        dbRef = FirebaseDatabase.getInstance().getReference("user")

        // Set an onclick listener for the register button
        binding.bregisterbtn.setOnClickListener {

            // Get user inputs from the EditText views
            val userName = binding.usernamebs.text.toString()
            val Email = binding.emailbs.text.toString()
            val address = binding.addressbs.text.toString()
            val userMobile = binding.editTextPhonebs.text.toString()
            val Nic = binding.NICnobs.text.toString()
            val password = binding.pwdbs.text.toString()

            // If required fields are not filled, show appropriate error messages
            if (Email.isEmpty()){
                Toast.makeText(this,"Please fill Email", Toast.LENGTH_LONG).show()
            }
            if (password.isEmpty()){
                Toast.makeText(this,"Please fill password", Toast.LENGTH_LONG).show()
            }
            if(Email.isEmpty()){
                Toast.makeText(this,"Please fill Email", Toast.LENGTH_LONG).show()
            }
            if(userMobile.isEmpty()){
                Toast.makeText(this,"Please fill Mobile number", Toast.LENGTH_LONG).show()
            }
            if((userMobile.length>10) && (userMobile.length<10)){
                Toast.makeText(this,"Please enter valid mobile number", Toast.LENGTH_LONG).show()
            }
            if( password.length<6){
                Toast.makeText(this,"password length max for 6", Toast.LENGTH_LONG).show()
            }

            // Check if all required fields are filled
            if (Email.isNotEmpty() && password.isNotEmpty()&&Nic.isNotEmpty() && Email.isNotEmpty()  && userMobile.isNotEmpty() && userName.isNotEmpty() && (userMobile.length==10)  && (password.length>5)) {

                // Create user with email and password using FirebaseAuth
                firebaseAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(this) {task->

                    // If user creation is successful
                    if (task.isSuccessful) {

                        // Show a success message
                        Toast.makeText(this,"SIGN UP SUCCESS", Toast.LENGTH_LONG).show()

                        // Get the current user and their UID
                        val auth1=auth.currentUser
                        var userId=auth1?.uid

                        // Create a UserModel object with user data
                        val dataInsert= UserModel(userId,userName,Email,userMobile,address,Nic,password,"bs")

                        // Add the user data to the Firebase Realtime Database
                        dbRef.child(userId!!).setValue(dataInsert).addOnSuccessListener {
                            Toast.makeText(this,"Created user", Toast.LENGTH_LONG).show()

                            // Redirect the user to the login activity
                            intent = Intent(applicationContext, LogIn::class.java)
                            startActivity(intent)
                        }.addOnFailureListener {err->
                            Toast.makeText(this,"Driver not Added ${err}", Toast.LENGTH_LONG).show()
                        }

                    } else {
                        // If user creation fails, show an error message
                        Toast.makeText(this, "Login fail", Toast.LENGTH_LONG).show()
                    }

                }

            }





        }
    }
}