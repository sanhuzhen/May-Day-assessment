package com.sanhuzhen.maydayassessment.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.sanhuzhen.maydayassessment.base.BaseActivity
import com.sanhuzhen.maydayassessment.databinding.ActivityRegisteredBinding
import com.sanhuzhen.maydayassessment.utils.toast

/**
 * @author: sanhuzhen
 * @date: 2024/05/01 12:00
 * @description:注册
 */
class RegisteredActivity: BaseActivity() {
    private lateinit var mSharedPreferences: SharedPreferences
    private val binding: ActivityRegisteredBinding by lazy {
        ActivityRegisteredBinding.inflate(layoutInflater)
    }
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mSharedPreferences = getSharedPreferences("user",Context.MODE_PRIVATE)
        binding.btnRegister.setOnClickListener {
            if (binding.etRegisterPassword2.text.toString().isEmpty()||binding.etRegisterAccount.text.toString().isEmpty()
                ||binding.etRegisterPassword1.text.toString().isEmpty()){
                toast(this,"用户名或密码不能为空")
            }else if (binding.etRegisterPassword1.text.toString()!=binding.etRegisterPassword2.text.toString()){
                toast(this,"两次密码不一致")
            }else{
                val editor = mSharedPreferences.edit()
                val account = binding.etRegisterAccount.text.toString()
                val password = binding.etRegisterPassword1.text.toString()
                editor.putString("account",account)
                editor.putString("password",password)
                editor.apply()
                toast(this,"注册成功")
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
                }
            }

        }
    }
