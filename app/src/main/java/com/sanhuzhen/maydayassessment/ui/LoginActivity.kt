package com.sanhuzhen.maydayassessment.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.sanhuzhen.maydayassessment.base.BaseActivity
import com.sanhuzhen.maydayassessment.databinding.ActivityLoginBinding
import com.sanhuzhen.maydayassessment.utils.toast

/**
 * @author: sanhuzhen
 * @date: 2024/05/01 10:12
 * @description:登录
 */

class LoginActivity: BaseActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var mSharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mSharedPreferences = getSharedPreferences("user",Context.MODE_PRIVATE)
        initEvent()
    }
    private fun initEvent() {
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this,RegisteredActivity::class.java) )
        }
        binding.btnLogin.setOnClickListener {
            if (binding.etLoginAccount.text.toString().isEmpty()||binding.etLoginPassword.text.toString().isEmpty()){
                toast(this,"用户名或密码不能为空")
            }else{
                val account = binding.etLoginAccount.text.toString()
                val password = binding.etLoginPassword.text.toString()
                if (mSharedPreferences.getString("account","").equals(account)&&
                    mSharedPreferences.getString("password","").equals(password)){
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    toast(this,"用户名或密码错误")
                }
            }
        }
        binding.ivQq.setOnClickListener {
            toast(this,"功能暂未开放")
        }
        binding.ivWechat.setOnClickListener {
            toast(this,"功能暂未开放")
        }
    }
}