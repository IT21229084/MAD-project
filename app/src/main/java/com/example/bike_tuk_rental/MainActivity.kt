package com.example.bike_tuk_rental

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity() : AppCompatActivity() {

    private lateinit var time_text_view: TextView
    private lateinit var time_picker_button: Button
    private lateinit var time_text_view2: TextView
    private lateinit var time_picker_button2: Button
    private lateinit var date_text_view1: TextView
    private lateinit var date_picker_button1: Button
    private lateinit var date_text_view2: TextView
    private lateinit var date_picker_button2: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bookings)

        date_picker_button1 = findViewById(R.id.date_picker_button1)
        date_text_view1 = findViewById(R.id.date_text_view1)

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
        }


        date_picker_button1.setOnClickListener {
            DatePickerDialog(
                this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        date_picker_button2 = findViewById(R.id.date_picker_button2)
        date_text_view2 = findViewById(R.id.date_text_view2)

        val myCalendar2 = Calendar.getInstance()

        val datePicker2 = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar2.set(Calendar.YEAR, year)
            myCalendar2.set(Calendar.MONTH, month)
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable2(myCalendar2)
        }

        date_picker_button2.setOnClickListener {
            DatePickerDialog(
                this, datePicker2, myCalendar2.get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                myCalendar2.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        time_text_view = findViewById(R.id.time_text_view)
        time_picker_button = findViewById(R.id.time_picker_button)

        time_picker_button.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                time_text_view.setText("$hourOfDay:$minute")
            }, startHour, startMinute, false).show()
        }
        time_text_view2 = findViewById(R.id.time_text_view2)
        time_picker_button2 = findViewById(R.id.time_picker_button2)

        time_picker_button2.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                time_text_view2.setText("$hourOfDay:$minute")
            }, startHour, startMinute, false).show()
        }
    }

        private fun updateLable(myCalendar: Calendar) {
            val myFormat = "dd-MM-yyyy"
            val simpleDateFormat = SimpleDateFormat(myFormat, Locale.UK)
            date_text_view1.setText(simpleDateFormat.format(myCalendar.time))


        }
        private fun updateLable2(myCalendar2: Calendar) {
            val myFormat = "dd-MM-yyyy"
            val simpleDateFormat2 = SimpleDateFormat(myFormat, Locale.UK)
            date_text_view2.setText(simpleDateFormat2.format(myCalendar2.time))


    }
}






