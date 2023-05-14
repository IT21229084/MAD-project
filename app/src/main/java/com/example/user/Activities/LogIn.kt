package com.example.user.Activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.user.Model.UserModel
import com.example.user.R
import com.example.user.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class LogIn : AppCompatActivity() {

    // Declare properties
    private lateinit var binding:ActivityLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the Firebase instances
        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("user")

        // Set the onClickListener for the login button
        binding.login.setOnClickListener {
            val email = binding.logEmail.text.toString()
            val pass = binding.logpwd.text.toString()

            //check email and password field empty
            if(email.isEmpty()){
                Toast.makeText(this,"Email field is Empty",Toast.LENGTH_LONG).show()
            }
            if(pass.isEmpty()){
                Toast.makeText(this,"Password field is Empty",Toast.LENGTH_LONG).show()
            }

            // Check if both the email and password fields are not empty
            if (email.isNotEmpty() && pass.isNotEmpty()) {

                // Sign in the user with the given email and password using Firebase Authentication
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Display a success message and retrieve the user data from the database
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                        databaseReference.child(firebaseAuth.currentUser!!.uid).addValueEventListener(object : ValueEventListener {

                            override fun onDataChange(snapshot: DataSnapshot) {

                                var user = snapshot.getValue(UserModel::class.java)!!

                                // Determine if the user is a customer or a business
                                if( user.userType == "cus") {
                                    intent = Intent(applicationContext, Profile::class.java)
                                    startActivity(intent)
                                } else if(user.userType == "bs") {
                                    intent = Intent(applicationContext, BusinessProfile::class.java)
                                    startActivity(intent)
                                }
                            }
                            override fun onCancelled(error: DatabaseError) {
                            }
                        })
                    }else {
                        Toast.makeText(this, "Login Fail" + it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Set the onClickListener for the login button and forgot Option
        binding.regbtn.setOnClickListener {
            intent = Intent(applicationContext, RegisterDir::class.java)
            startActivity(intent)
        }
        binding.forgotpwd.setOnClickListener {
            intent = Intent(this,forgotPassword::class.java)
            startActivity(intent)
        }
    }
}