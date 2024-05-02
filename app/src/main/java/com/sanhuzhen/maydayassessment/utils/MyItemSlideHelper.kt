package com.sanhuzhen.maydayassessment.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * @author sanhuzhen
 * @data 2024/05/01 19:25
 * @description:写一个Helper来实现侧滑删除，修改，置顶功能
 */
class MyItemSlideHelper: RecyclerView.OnItemTouchListener {
    private var lastX: Float = 0f
    private var lastY: Float = 0f
    private var callback: ItemTouchHelper.Callback? = null

    override fun onInterceptTouchEvent(rv: RecyclerView, e: android.view.MotionEvent): Boolean {
        return false
    }
    override fun onTouchEvent(rv: RecyclerView, e: android.view.MotionEvent) {
        TODO("Not yet implemented")
    }
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        TODO("Not yet implemented")
    }

}