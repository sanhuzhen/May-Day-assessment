package com.sanhuzhen.maydayassessment.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import com.sanhuzhen.maydayassessment.base.BaseActivity
import com.sanhuzhen.maydayassessment.databinding.ActivityAddtaskBinding
import com.sanhuzhen.maydayassessment.db.MyDatabaseHelper
import com.sanhuzhen.maydayassessment.utils.selectCalendar
import com.sanhuzhen.maydayassessment.utils.toast

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
        setContentView(binding.root)
        //创建数据库
        val dbHelper = MyDatabaseHelper(this,"Task.db",1)
        val db = dbHelper.writableDatabase
        binding.btnAdd.setOnClickListener {
            val name = binding.etTextName.text.toString()
            val time = binding.tvTextTime.text.toString()
            val status  = false
            val description = binding.etRemark.text.toString()
            if (name.isEmpty()||time.isEmpty()||description.isEmpty()){
                toast(this,"请输入完整信息！！！")
            }else{
                val values = ContentValues().apply {
                    put("name",name)
                    put("time",time)
                    put("status",status)
                    put("description",description)
                }
                toast(this,"添加成功！！！")
                db.insert("Task",null,values)
                Log.d("you","----------- 添加成功 -----------")
                finish()
            }
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.tvTextTime.setOnClickListener {
            selectCalendar(this,binding.tvTextTime)
        }
    }
}


