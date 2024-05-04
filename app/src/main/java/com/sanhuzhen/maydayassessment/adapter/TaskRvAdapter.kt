package com.sanhuzhen.maydayassessment.adapter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sanhuzhen.maydayassessment.R
import com.sanhuzhen.maydayassessment.bean.Task
import com.sanhuzhen.maydayassessment.db.MyDatabaseHelper
import com.sanhuzhen.maydayassessment.utils.ItemTouchMoveListener
import com.sanhuzhen.maydayassessment.utils.selectCalendar
import java.util.Calendar
import java.util.Collections


/**
 * @author sanhuzhen
 * @data 2024/05/01 19:19
 * @description:rv的adapter
 */
class TaskRvAdapter(private val context: Context): ListAdapter<Task,TaskRvAdapter.TheViewHolder>(object:
    DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.name == newItem.name&&oldItem.time == newItem.time&&oldItem.status == newItem.status&&oldItem.image == newItem.image
    }
}), ItemTouchMoveListener{

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        val task = getItem(position)

        holder.apply {
            taskName.text = task.name
            taskTime.text = task.time
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(task.id.toString(), Context.MODE_PRIVATE)
            taskBox.isChecked = sharedPreferences.getBoolean(task.id.toString(), false)
            image.setImageResource(task.image)
            // 添加保存 CheckBox 状态到 SharedPreferences 的逻辑
            taskBox.setOnCheckedChangeListener { _, isChecked ->
                sharedPreferences.edit().putBoolean(task.id.toString(), isChecked).apply()
                if (taskBox.isChecked){
                    itemView.setBackgroundColor(R.color.huisde)
                    submitList(currentList)
                }
            }

        }
    }
    //过滤重新排序

    override fun onItemRemove(position: Int,itemView: View): Boolean {
        val task = getItem(position)
        val dataList = currentList.toMutableList()
        return if (dataList.size > 0) {
            if (position != RecyclerView.NO_POSITION) {
                dataList.removeAt(position)
                //删除数据库中的数据
                val db = MyDatabaseHelper(itemView.context, "Task.db", 1).writableDatabase
                db.delete("task", "name=?", arrayOf(task.name))
                db.close()
                submitList(dataList)
            }
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
            val options = arrayOf("修改", "删除","置顶")
            AlertDialog.Builder(itemView.context)
                .setTitle("选择操作")
                .setItems(options) { _, which ->
                    when (which) {
                        0 -> showEditDialog(task)
                        1 -> showDeleteConfirmationDialog(task)
                        2 -> top()
                    }
                }
                .show()
        }
        @SuppressLint("MissingInflatedId", "InflateParams")
        private fun showEditDialog(task: Task) {
            val bottomSheetDialog = BottomSheetDialog(itemView.context)
            val view = LayoutInflater.from(itemView.context).inflate(R.layout.edit_task_dialog, null)
            val taskNameEditText = view.findViewById<EditText>(R.id.et2_textName)
            val taskTimeEditText = view.findViewById<TextView>(R.id.tv2_textTime)
            val taskRemark = view.findViewById<EditText>(R.id.et2_remark)
            taskNameEditText.setText(task.name)
            taskTimeEditText.text = task.time
            taskRemark.setText(task.description)
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.setCanceledOnTouchOutside(true)
            taskTimeEditText.setOnClickListener {
                selectCalendar(itemView.context, taskTimeEditText)
            }
            view.findViewById<Button>(R.id.btn_update).setOnClickListener {
                itemView.post {
                    updateTask(task, taskNameEditText, taskTimeEditText, taskRemark)
                }
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.show()
        }
        private fun updateTask(task: Task, taskNameEditText: EditText, taskTimeEditText: TextView, taskRemark: EditText) {
            val db = MyDatabaseHelper(itemView.context, "Task.db", 1).writableDatabase

            val values = ContentValues().apply {
                put("taskName", taskNameEditText.text.toString())
                put("taskTime", taskTimeEditText.text.toString())
                put("taskRemark", taskRemark.text.toString())
            }

            val selection = "taskId = ?"
            val selectionArgs = arrayOf(task.id.toString())
            db.update("TaskTable", values, selection, selectionArgs)

            db.close()
        }

        private fun top(){
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val dataList = currentList.toMutableList()
                val task = getItem(position)
                dataList.removeAt(position)
                dataList.add(0, task)
                submitList(dataList)
            }
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