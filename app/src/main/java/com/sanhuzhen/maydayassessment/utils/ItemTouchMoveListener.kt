package com.sanhuzhen.maydayassessment.utils


// 使用接口回调
interface ItemTouchMoveListener {
    fun onItemRemove(position: Int): Boolean
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
}