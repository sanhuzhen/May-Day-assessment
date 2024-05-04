package com.sanhuzhen.maydayassessment.utils

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * @author sanhuzhen
 * @data 2024/05/03 10:19
 * @description:使用ItemTouchHelper实现侧滑删除
 */
class MyItemTouchHelperCallback: ItemTouchHelper.Callback() {
    private lateinit var mItemTouchMoveListener: ItemTouchMoveListener
    //Callback判断滑动动作
    @SuppressLint("NotConstructor")
    public fun MyItemTouchHelperCallback(itemTouchMoveListener: ItemTouchMoveListener) {
        mItemTouchMoveListener = itemTouchMoveListener
    }
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //监听方向
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val dragFlag = ItemTouchHelper.DOWN or ItemTouchHelper.UP
        return makeMovementFlags(dragFlag, swipeFlag)
    }
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if (viewHolder.itemViewType != target.itemViewType) {
            return false
        }
        return mItemTouchMoveListener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)

    }
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mItemTouchMoveListener.onItemRemove(viewHolder.adapterPosition, viewHolder.itemView)
    }

}