package com.example.user.Activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.user.Model.UserModel
import com.example.user.R
import com.example.user.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val auth = Firebase.auth
        dbRef = FirebaseDatabase.getInstance().getReference("user")



        binding.cusregisterbtn.setOnClickListener {
            val userName = binding.cususername.text.toString()
            val Email = binding.cusemail.text.toString()
            val address = binding.cusaddress.text.toString()
            val userMobile = binding.cusmobileNo.text.toString()
            val Nic = binding.cusNICno.text.toString()
            val password = binding.cuspwd.text.toString()

            if (Email.isNotEmpty() && password.isNotEmpty()&&Nic.isNotEmpty() && Email.isNotEmpty()  && userMobile.isNotEmpty() && userName.isNotEmpty() && (userMobile.length==10)  && (password.length>5)) {

                firebaseAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(this) {task->

                    if (task.isSuccessful) {
                        Toast.makeText(this,"SIGN UP SUCCESS", Toast.LENGTH_LONG).show()
                        val auth1=auth.currentUser
                        var userId=auth1?.uid

                        val dataInsert=UserModel(userId,userName,Email,userMobile,address,Nic,password,"cus")
                        dbRef.child(userId!!).setValue(dataInsert).addOnSuccessListener {
                            Toast.makeText(this,"Created user", Toast.LENGTH_LONG).show()
                            intent = Intent(applicationContext, LogIn::class.java)
                            startActivity(intent)
                        }.addOnFailureListener {err->
                            Toast.makeText(this,"Driver not Added ${err}", Toast.LENGTH_LONG).show()
                        }

                    } else {
                        Toast.makeText(this, "Login fail", Toast.LENGTH_LONG).show()
                    }

                }

            } else {
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
            }


        }
    }


}