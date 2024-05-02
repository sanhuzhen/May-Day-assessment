package com.sanhuzhen.maydayassessment.ui

import android.content.Intent
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

/**
 * @author: sanhuzhen
 * @date: 2024/05/01 17:30
 * @description:任务
 */

class AssignmentFragment: Fragment() {
    private val taskList = ArrayList<Task>()
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
        val task = Task(R.drawable.ic_launcher_foreground,"任务一","任务描述","2024-05-01","是")
        addTask(task)
        initEvent()
        val layoutManager = LinearLayoutManager(this.activity)
        binding.rvTask.layoutManager = layoutManager
        TaskAdapter.submitList(taskList)
        binding.rvTask.adapter = TaskAdapter
    }
    //初始化事件
    private fun initEvent() {
        binding.btAdd.setOnClickListener {
            startActivity(Intent(this.context,AddTaskActivity::class.java))
        }
    }
    private fun addTask(task: Task) {
        taskList.add(task)
    }
}