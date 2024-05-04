package com.sanhuzhen.maydayassessment.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.sanhuzhen.maydayassessment.base.BaseActivity

class PointActivity: BaseActivity() {
    private val mSharedPreferences: SharedPreferences = getSharedPreferences("point", Context.MODE_PRIVATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}