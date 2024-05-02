package com.sanhuzhen.maydayassessment.ui

import android.os.Bundle
import com.sanhuzhen.maydayassessment.R
import com.sanhuzhen.maydayassessment.base.BaseActivity
import com.sanhuzhen.maydayassessment.databinding.ActivityAddtaskBinding
import com.sanhuzhen.maydayassessment.db.MyDatabaseHelper

/**
 * @author sanhuzhen
 * @data 2024/05/01 17:09
 * @description:添加任务
 */
class AddTaskActivity: BaseActivity() {
    private val binding: ActivityAddtaskBinding by lazy {
        ActivityAddtaskBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addtask)
        //创建数据库
        val dbHelper = MyDatabaseHelper(this,"Task.db",1)
        binding.btnAdd.setOnClickListener {
            dbHelper.writableDatabase
        }
    }
}


