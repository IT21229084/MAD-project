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

    private lateinit var binding:ActivityLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("user")

        binding.login.setOnClickListener {
            val email = binding.logEmail.text.toString()
            val pass = binding.logpwd.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                        databaseReference.child(firebaseAuth.currentUser!!.uid).addValueEventListener(object :
                            ValueEventListener {

                            override fun onDataChange(snapshot: DataSnapshot) {
                                //retrieve values from the db and convert them to user data class
                                var user = snapshot.getValue(UserModel::class.java)!!

                                if( user.userType == "cus") {
                                    intent = Intent(applicationContext, BusinessProfile::class.java)
                                    startActivity(intent)
                                } else {
                                    intent = Intent(applicationContext, BusinessProfile::class.java)
                                    startActivity(intent)
                                }
//                                intent = Intent(applicationContext, Profile::class.java)
//                                startActivity(intent)

                            }
                            override fun onCancelled(error: DatabaseError) {

                            }
                        })
                    }else {
                        Toast.makeText(
                            this,
                            "Login Fail" + it.exception.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                if(email.isEmpty()){
                    Toast.makeText(this,"Email field is Empty",Toast.LENGTH_LONG).show()
                }
                if(pass.isEmpty()){
                    Toast.makeText(this,"Password field is Empty",Toast.LENGTH_LONG).show()
                }
            }

        }

        binding.regbtn.setOnClickListener {
            intent = Intent(applicationContext, RegisterDir::class.java)
            startActivity(intent)
        }


    }


}