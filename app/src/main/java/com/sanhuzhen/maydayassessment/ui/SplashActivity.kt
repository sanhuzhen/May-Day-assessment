package com.sanhuzhen.maydayassessment.ui

import android.annotation.SuppressLint
import android.content.Intent
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Thread{
            Thread.sleep(1000)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }.start()
    }
}