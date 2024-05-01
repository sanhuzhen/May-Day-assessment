package com.sanhuzhen.maydayassessment.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.sanhuzhen.maydayassessment.R
import com.sanhuzhen.maydayassessment.base.BaseActivity

/**
 * @author: sanhuzhen
 * @date: 2024/05/01 12:00
 * @description: 闪屏页
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity() {
    private val mSharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("user", Context.MODE_PRIVATE)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (mSharedPreferences.getString("account","")==""){
                java.lang.Thread {
                    java.lang.Thread.sleep(1000)
                    startActivity(
                        android.content.Intent(
                            this,
                            com.sanhuzhen.maydayassessment.ui.LoginActivity::class.java
                        )
                    )
                    finish()
                }.start()
            }else{
            java.lang.Thread {
                java.lang.Thread.sleep(1000)
                startActivity(
                    android.content.Intent(
                        this,
                        com.sanhuzhen.maydayassessment.ui.MainActivity::class.java
                    )
                )
                finish()
            }.start()
        }

    }
}