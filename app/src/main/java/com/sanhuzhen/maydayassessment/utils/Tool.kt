package com.sanhuzhen.maydayassessment.utils

import android.content.Context
import android.widget.Toast

fun toast(context: Context, word: String){
    Toast.makeText(context,word, Toast.LENGTH_SHORT).show()
}