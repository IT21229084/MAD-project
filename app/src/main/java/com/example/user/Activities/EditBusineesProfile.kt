package com.example.user.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.user.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditBusineesProfile : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    private lateinit var ETUserName: EditText
    private lateinit var ETEmail: EditText
    private lateinit var ETMobileNo: EditText
    private lateinit var ETAddress: EditText
    private lateinit var ETNIC: EditText
    private lateinit var ETpassword: EditText
    private lateinit var btnupdate: Button
    private lateinit var btnDelete: Button
    private lateinit var editbtn: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_businees_profile)

        auth = FirebaseAuth.getInstance()
        var uid = auth.currentUser?.uid.toString()
        ETUserName = findViewById(R.id.usernamebs)
        ETEmail = findViewById(R.id.emailbs)
        ETMobileNo = findViewById(R.id.MobileNobs)
        ETAddress = findViewById(R.id.addressbs)
        ETNIC = findViewById(R.id.NICbs)
        ETpassword = findViewById(R.id.passwordbs)
        editbtn = findViewById(R.id.confirmbs)
        btnDelete = findViewById(R.id.delbs)

        databaseReference = FirebaseDatabase.getInstance().getReference("user")


        //fetch data from the intent
        val userName = intent.getStringExtra("userName")
        val email = intent.getStringExtra("email")
        val mobile = intent.getStringExtra("mobileNo")
        val address = intent.getStringExtra("address")
        val nic = intent.getStringExtra("nic")
        val password = intent.getStringExtra("password")

        //bind valuees to editTexts
        ETUserName.setText(userName)
        ETEmail.setText(email)
        ETMobileNo.setText(mobile)
        ETAddress.setText(address)
        ETNIC.setText(nic)
        ETpassword.setText(password)

        editbtn.setOnClickListener {
            var UserName = ETUserName.text.toString()
            var Email = ETEmail.text.toString()
            var MobileNo = ETMobileNo.text.toString()
            var address = ETAddress.text.toString()
            var nic = ETNIC.text.toString()
            var password = ETpassword.text.toString()

            if (UserName.isEmpty()){
                Toast.makeText(this,"Please fill Name", Toast.LENGTH_LONG).show()
            }
            if (Email.isEmpty()){
                Toast.makeText(this,"Please fill Email", Toast.LENGTH_LONG).show()
            }
            if(MobileNo.isEmpty()){
                Toast.makeText(this,"Please fill Contact No", Toast.LENGTH_LONG).show()
            }


            if (UserName.isNotEmpty() && Email.isNotEmpty()  && MobileNo.isNotEmpty()) {

                val map = HashMap<String,Any>()
                //add data to hashMap
                map["userName"] = UserName
                map["email"] = Email
                map["mobileNo"] = MobileNo
                map["address"] = address
                map["nic"] = nic
                map["password"] = password


                //update database from hashMap
                databaseReference.child(uid).updateChildren(map).addOnCompleteListener {
                    if( it.isSuccessful){
                        intent = Intent(applicationContext, BusinessProfile::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnDelete.setOnClickListener {
            Toast.makeText(this, "Account Deleted", Toast.LENGTH_SHORT).show()
            //delete account
            var currUser = auth.currentUser
            currUser?.delete()?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //delete user data entry from db
                        databaseReference.child(uid).removeValue().addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(this, "Account Deleted", Toast.LENGTH_SHORT).show()
                                intent = Intent(applicationContext, LogIn::class.java)
                                startActivity(intent)
                            }
                        }
                    } else {
                        Toast.makeText(this, "Failed to delete the account", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}