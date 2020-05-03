package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // The listener for the select format onClick event
        btnSelectFormat.setOnClickListener { view ->
            clickFormatPicker(view)
        }

        // The listener for the select date onCLick event
        btnSelectDate.setOnClickListener{view ->
            clickDatePicker(view)
        }
    }
    // Defining the function that will run after select format onClick event.
    private fun clickFormatPicker(view: View){
        if(tvAgeInPersepective.text != ""){
            var currentAgeInUnits = "${tvAgeInPersepective.text}".toInt()
            if(btnSelectFormat.text == "in Minutes"){
                tvAgeInPersepective.text = "${currentAgeInUnits / 1440}"
            }else {
                tvAgeInPersepective.text = "${currentAgeInUnits * 1440}"
            }
        }

        if(btnSelectFormat.text == "in Minutes"){
            btnSelectFormat.text = getString(R.string.return_days)
            ageUnits.text = getString(R.string.unit_p_days)
        }else{
            btnSelectFormat.text = getString(R.string.return_mintues)
            ageUnits.text = getString(R.string.unit_p_minutes)
        }

    }
    // Defining the function that will run after select date onClick event.
    private fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                _, selectedYear, selectedMonth, selectedDayOfMonth ->

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.text = selectedDate

            val simpleDate = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val theDate = simpleDate.parse(selectedDate)
            val selectedDateMinutes = theDate!!.time / 60000
            val currentDate = simpleDate.parse(simpleDate.format(System.currentTimeMillis()))
            val currentDateMinutes = currentDate!!.time/ 60000
            val difMinutes = currentDateMinutes-selectedDateMinutes


            if(btnSelectFormat.text == getString(R.string.return_mintues)){
                tvAgeInPersepective.text = difMinutes.toString()
            }else{
                val difDays = difMinutes / 1440
                tvAgeInPersepective.text = difDays.toString()
            }



        }, year, month, day)
        datePickerDialog.datePicker.maxDate = Date().time - 84600000
        datePickerDialog.show()
    }
}
