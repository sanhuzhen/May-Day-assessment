package com.sanhuzhen.maydayassessment.utils

import android.app.DatePickerDialog
import android.content.Context
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

fun toast(context: Context, word: String){
    Toast.makeText(context,word, Toast.LENGTH_SHORT).show()
}
fun selectCalendar(context: Context,textView: TextView){
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    //引入官方日历
    val datePickerDialog = DatePickerDialog(
        context,
        DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
            // 在日期被选择后
            val birthday = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            textView.text = birthday
        },
        year, month, day
    )
    datePickerDialog.show()
}