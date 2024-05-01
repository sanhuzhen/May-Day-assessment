package com.sanhuzhen.maydayassessment.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.sanhuzhen.maydayassessment.base.BaseActivity
import com.sanhuzhen.maydayassessment.databinding.ActivityFeedbackBinding

/**
 * @author: sanhuzhen
 * @date: 2024/05/01 14:34
 * @description: 意见反馈
 */
class FeedBackActivity: BaseActivity() {
    private val binding: ActivityFeedbackBinding by lazy {
        ActivityFeedbackBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val content = binding.etFeedback.text
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnFeedback.setOnClickListener {
            if (content.isEmpty()) {
                Toast.makeText(this, "请输入内容！！！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                AlertDialog.Builder(this).apply {
                    setTitle("提交成功！！！")
                    setMessage("感谢您的宝贵意见！！！")
                    setCancelable(false)
                    setPositiveButton("确定") { _, _ ->
                        finish()
                    }
                    show()
                }
            }

        }
    }
}