package com.example.user.Activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.user.Model.Payment
import com.example.user.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class add_payment : AppCompatActivity() {

    private lateinit var month: Spinner
    private lateinit var year: Spinner
    private lateinit var name : EditText
    private lateinit var card : EditText
    private lateinit var ccv : EditText
    private lateinit var reason : EditText
    private lateinit var amount : TextView
    private lateinit var mDatabaseRef: DatabaseReference
    private lateinit var addBtn : Button
    private lateinit var radioGroup : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_payment)
        name = findViewById(R.id.add_card_name) as EditText
        card = findViewById(R.id.add_card_number) as EditText
        ccv = findViewById(R.id.add_card_ccv) as EditText
        reason = findViewById(R.id.add_reason) as EditText
        month = findViewById(R.id.add_month) as Spinner
        year = findViewById(R.id.add_year) as Spinner
        amount = findViewById(R.id.add_card_amount) as TextView
        addBtn = findViewById(R.id.add_btn) as Button
        radioGroup = findViewById(R.id.radioG) as RadioGroup

        val intent = intent
        amount.setText(intent.getStringExtra("amount"))
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("payments")

        val month_data = arrayOf("01", "02", "03","04", "05", "06","07", "08", "09","10", "11", "12")

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, month_data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        month.setAdapter(adapter)

        val year_data = arrayOf("2022", "2023","2024", "2025", "2026","2027", "2028", "2029")

        val adapter1: ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, year_data)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        year.setAdapter(adapter1)

        addBtn.setOnClickListener {
            if (radioGroup.checkedRadioButtonId != -1) {
                if(name.text.toString()!=""){
                    if(card.text.toString()!=""){
                        if(ccv.text.toString()!=""){
                            if(reason.text.toString()!=""){

                                var btn_id = radioGroup.getCheckedRadioButtonId()
                                val radioButton = findViewById<View>(btn_id) as RadioButton

                                val add_temp = Payment(
                                    name.text.toString(),
                                    card.text.toString(),
                                    reason.text.toString(),
                                    ccv.text.toString().toInt(),
                                    radioButton.getText().toString(),
                                    month.getSelectedItem().toString(),
                                    year.getSelectedItem().toString(),
                                    intent.getStringExtra("amount").toString().toDouble()
                                )

                                val temp_Id = mDatabaseRef.push().getKey()
                                mDatabaseRef.child(temp_Id.toString()).setValue(add_temp)
                                Toast.makeText(this, "Insert Successful!", Toast.LENGTH_SHORT).show()
                                name.setText("")
                                card.setText("")
                                reason.setText("")
                                ccv.setText("")
                                month.setSelection(0)
                                year.setSelection(0)
                            }else{
                                Toast.makeText(this, "Reason Required!", Toast.LENGTH_SHORT).show()
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