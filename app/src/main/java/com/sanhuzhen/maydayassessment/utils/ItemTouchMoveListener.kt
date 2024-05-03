package com.sanhuzhen.maydayassessment.utils

import android.view.View


// 使用接口回调
interface ItemTouchMoveListener {
    fun onItemRemove(position: Int,itemView: View): Boolean
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
}