package com.sanhuzhen.maydayassessment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.maydayassessment.R
import com.sanhuzhen.maydayassessment.bean.Task
import com.sanhuzhen.maydayassessment.utils.ItemTouchMoveListener
import java.util.Collections


/**
 * @author sanhuzhen
 * @data 2024/05/01 19:19
 * @description:rv的adapter
 */
open class TaskRvAdapter: ListAdapter<Task,TaskRvAdapter.TheViewHolder>(object:
    DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.name == newItem.name&&oldItem.time == newItem.time&&oldItem.status == newItem.status&&oldItem.image == newItem.image
    }
}), ItemTouchMoveListener {
    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        val task = getItem(position)
        holder.apply {
            taskName.text = task.name
            taskTime.text = task.time
            taskStatus.text = task.status
            image.setImageResource(task.image)
        }
        when (task.status) {

            "否" -> {
                holder.taskStatus.setTextColor(holder.itemView.context.getColor(R.color.red))
            }

            "是" -> {
                holder.taskStatus.setTextColor(holder.itemView.context.getColor(R.color.green))
            }
        }
    }

    override fun onItemRemove(position: Int): Boolean {
        val dataList = currentList.toMutableList()
        return if (dataList.size > 0) {
            dataList.removeAt(position)
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
        val taskStatus: TextView = itemView.findViewById(R.id.tv_taskIsFinish)
    }
}