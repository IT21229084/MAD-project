package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.user.Model.Payment
import com.example.user.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class edit_payment : AppCompatActivity() {

    private lateinit var month: Spinner
    private lateinit var year: Spinner
    private lateinit var name : EditText
    private lateinit var card : EditText
    private lateinit var ccv : EditText
    private lateinit var reason : EditText
    private lateinit var amount : TextView
    private lateinit var mDatabaseRef: DatabaseReference
    private lateinit var editBtn : Button
    private lateinit var radioGroup : RadioGroup
    var id: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_payment)
        name = findViewById(R.id.edit_card_name) as EditText
        card = findViewById(R.id.edit_card_number) as EditText
        ccv = findViewById(R.id.edit_card_ccv) as EditText
        reason = findViewById(R.id.edit_reason) as EditText
        month = findViewById(R.id.edit_month) as Spinner
        year = findViewById(R.id.edit_year) as Spinner
        amount = findViewById(R.id.edit_card_amount) as TextView
        editBtn = findViewById(R.id.edit_btn) as Button
        radioGroup = findViewById(R.id.radioG) as RadioGroup

        //dropdown create
        val month_data = arrayOf("01", "02", "03","04", "05", "06","07", "08", "09","10", "11", "12")

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, month_data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        month.setAdapter(adapter)

        val year_data = arrayOf("2022", "2023","2024", "2025", "2026","2027", "2028", "2029")

        val adapter1: ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, year_data)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        year.setAdapter(adapter1)

        val intent = intent
        id = intent.getStringExtra("id")
        reason.setText(intent.getStringExtra("reason"))
        card.setText(intent.getStringExtra("card"))
        ccv.setText(intent.getStringExtra("ccv"))
        name.setText(intent.getStringExtra("name"))
        month.setSelection(adapter.getPosition(intent.getStringExtra("month")))
        year.setSelection(adapter1.getPosition(intent.getStringExtra("year")))
        amount.setText(intent.getStringExtra("amount"))
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("payments")

        //radio buttons
        if(intent.getStringExtra("type").equals("Visa")){
            println("visa")
            val radioButton = findViewById(R.id.edit_radio1) as RadioButton
            radioButton.setChecked(true)
        }else if(intent.getStringExtra("type").equals("American")){
            val radioButton = findViewById(R.id.edit_radio2) as RadioButton
            radioButton.setChecked(true)
        }else if(intent.getStringExtra("type").equals("Master")){
            println("master")
            val radioButton = findViewById(R.id.edit_radio3) as RadioButton
            radioButton.setChecked(true)
        }

        //validations
        editBtn.setOnClickListener {
            if (radioGroup.checkedRadioButtonId != -1) {
                if(name.text.toString()!=""){
                    if(card.text.toString()!=""){
                        if(ccv.text.toString()!=""){
                            if(reason.text.toString()!=""){

                                var btn_id = radioGroup.getCheckedRadioButtonId()
                                val radioButton = findViewById<View>(btn_id) as RadioButton

                                val edit_temp = Payment(
                                    name.text.toString(),
                                    card.text.toString(),
                                    reason.text.toString(),
                                    ccv.text.toString().toInt(),
                                    radioButton.getText().toString(),
                                    month.getSelectedItem().toString(),
                                    year.getSelectedItem().toString(),
                                    intent.getStringExtra("amount").toString().toDouble()
                                )

                                mDatabaseRef.child(id.toString()).setValue(edit_temp)
                                Toast.makeText(this, "Update Successful!", Toast.LENGTH_SHORT).show()
                                name.setText("")
                                card.setText("")
                                reason.setText("")
                                ccv.setText("")
                                month.setSelection(0)
                                year.setSelection(0)
                                startActivity(Intent(this, All_Payments::class.java))
                            }else{
                                Toast.makeText(this, "Reason Required!", Toast.LENGTH_SHORT).show()//popup messages
                            }
                        }else{
                            Toast.makeText(this, "CCV Code Required!", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, "Card Number Required!", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Name Required!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Select Visa Card Type", Toast.LENGTH_LONG).show()
            }
        }
    }
}