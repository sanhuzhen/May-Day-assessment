package com.sanhuzhen.maydayassessment.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sanhuzhen.maydayassessment.utils.toast

class MyDatabaseHelper(private val context: Context, name: String, version: Int):
    SQLiteOpenHelper(context,name,null,version){
        private val createTask = "create table Task (" +
                "id integer primary key autoincrement," +
                "name text," +
                "time text," +
                "status text,"+
                "description text)"

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(createTask)
            toast(this.context,"添加成功")
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        }
}