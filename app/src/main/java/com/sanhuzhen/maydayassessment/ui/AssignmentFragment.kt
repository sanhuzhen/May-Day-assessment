package com.sanhuzhen.maydayassessment.ui

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.maydayassessment.R
import com.sanhuzhen.maydayassessment.adapter.TaskRvAdapter
import com.sanhuzhen.maydayassessment.bean.Task
import com.sanhuzhen.maydayassessment.databinding.FragmentAssignmentBinding
import com.sanhuzhen.maydayassessment.db.MyDatabaseHelper

/**
 * @author: sanhuzhen
 * @date: 2024/05/01 17:30
 * @description:任务
 */

class AssignmentFragment: Fragment() {
    private val TaskAdapter: TaskRvAdapter by lazy {
        TaskRvAdapter()
    }
    private val binding: FragmentAssignmentBinding by lazy {
        FragmentAssignmentBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEvent()
        val layoutManager = LinearLayoutManager(this.activity)
        binding.rvTask.layoutManager = layoutManager
        binding.rvTask.adapter = TaskAdapter
        val dataList = loadDataFromDatabase()
        TaskAdapter.submitList(dataList)
    }
    //初始化事件
    private fun initEvent() {
        binding.btAssignmentAdd.setOnClickListener {
            startActivity(Intent(this.context,AddTaskActivity::class.java))
        }
    }
    @SuppressLint("Range", "Recycle")
    private fun loadDataFromDatabase(): List<Task> {
        val dbHelper = this.activity?.let { MyDatabaseHelper(it,"Task.db",1) }
        val db = dbHelper?.readableDatabase
        val dataList = mutableListOf<Task>()
        val cursor: Cursor? = db?.query("task",null,null,null,null,null,null)
        while (cursor!!.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val time = cursor.getString(cursor.getColumnIndex("time"))
            val status = cursor.getString(cursor.getColumnIndex("status"))
            val description = cursor.getString(cursor.getColumnIndex("description"))
            val task = Task(R.drawable.ic_launcher_foreground,name,description,time,status)
            dataList.add(task)
        }
        return dataList
    }

    override fun onResume() {
        super.onResume()
        val dataList = loadDataFromDatabase()
        if (dataList.isNotEmpty()){
            TaskAdapter.submitList(dataList)
        }
    }
}