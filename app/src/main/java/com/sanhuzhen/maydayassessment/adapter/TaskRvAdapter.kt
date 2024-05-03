package com.sanhuzhen.maydayassessment.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.maydayassessment.R
import com.sanhuzhen.maydayassessment.bean.Task
import com.sanhuzhen.maydayassessment.db.MyDatabaseHelper
import com.sanhuzhen.maydayassessment.utils.ItemTouchMoveListener
import java.util.Collections


/**
 * @author sanhuzhen
 * @data 2024/05/01 19:19
 * @description:rv的adapter
 */
class TaskRvAdapter: ListAdapter<Task,TaskRvAdapter.TheViewHolder>(object:
    DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.name == newItem.name&&oldItem.time == newItem.time&&oldItem.status == newItem.status&&oldItem.image == newItem.image
    }
}), ItemTouchMoveListener{
    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        val task = getItem(position)
        holder.apply {
            taskName.text = task.name
            taskTime.text = task.time
            image.setImageResource(task.image)
        }

    }
    override fun onItemRemove(position: Int,itemView: View): Boolean {
        val dataList = currentList.toMutableList()
        val task = getItem(position)
        return if (dataList.size > 0) {
            dataList.removeAt(position)
            val db = MyDatabaseHelper(itemView.context, "Task.db", 1).writableDatabase
            db.delete("task", "name=?", arrayOf(task.name))
            db.close()
            submitList(dataList)
            true
        } else {
            false
        }
    }
    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(currentList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheViewHolder {
        return TheViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false)
        )
    }

    inner class TheViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView)
        val taskName: TextView = itemView.findViewById(R.id.tv_taskName)
        val taskTime: TextView = itemView.findViewById(R.id.tv_taskTime)
        val taskBox: CheckBox = itemView.findViewById(R.id.checkBox)
        init {
            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val task = getItem(position)
                    showEditOrDeleteDialog(task)
                }
                true
            }
        }
        private fun showEditOrDeleteDialog(task: Task) {
            val options = arrayOf("修改", "删除")
            AlertDialog.Builder(itemView.context)
                .setTitle("选择操作")
                .setItems(options) { _, which ->
                    when (which) {
                        0 -> showEditDialog(task)
                        1 -> showDeleteConfirmationDialog(task)
                    }
                }
                .show()
        }

        @SuppressLint("MissingInflatedId")
        private fun showEditDialog(task: Task) {
//            // 创建修改对话框并处理修改逻辑
//            val dialogView = LayoutInflater.from(itemView.context).inflate(R.layout.edit_dialog, null)
//            val editTextName = dialogView.findViewById<EditText>(R.id.et_textName)
//            val editTextTime = dialogView.findViewById<EditText>(R.id.editTextTime)
//
//            editTextName.setText(task.name)
//            editTextTime.setText(task.time)
//
//            AlertDialog.Builder(itemView.context)
//                .setTitle("修改任务")
//                .setView(dialogView)
//                .setPositiveButton("确定") { _, _ ->
//                    val newName = editTextName.text.toString()
//                    val newTime = editTextTime.text.toString()
//                    if (newName.isNotEmpty() && newTime.isNotEmpty()) {
//                        // 更新数据到数据库
////                        updateTaskInDatabase(task, newName, newTime)
//                    } else {
//                        // 提示用户输入有效的名称和时间
//                        Toast.makeText(itemView.context, "名称和时间不能为空", Toast.LENGTH_SHORT).show()
//                    }
//                }
//                .setNegativeButton("取消", null)
//                .show()
        }
        private fun showDeleteConfirmationDialog(task: Task) {
            AlertDialog.Builder(itemView.context)
                .setTitle("确认删除")
                .setMessage("确定要删除该项吗？")
                .setPositiveButton("确定") { _, _ ->
                    // 执行删除操作
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val dataList = currentList.toMutableList()
                        dataList.removeAt(position)
                        //删除数据库中的数据
                        deleteTaskFromDatabase(task)
                        submitList(dataList)
                    }
                }
                .setNegativeButton("取消", null)
                .show()
        }
        private fun deleteTaskFromDatabase(task: Task) {
            val db = MyDatabaseHelper(itemView.context, "Task.db", 1).writableDatabase
            db.delete("task", "name=?", arrayOf(task.name))
            db.close()
        }
    }
}