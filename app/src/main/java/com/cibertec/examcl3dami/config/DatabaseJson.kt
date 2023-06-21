package com.cibertec.examcl3dami.config

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log

const val TABLE_TODO_JSON = "TableTodos"
private const val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_TODO_JSON (" + "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," + "titleTodo TEXT," + "completedTodo TEXT)"
private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_TODO_JSON"

class DatabaseJson(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Temporal.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        db.execSQL("INSERT INTO $TABLE_TODO_JSON(${BaseColumns._ID},titleTodo,completedTodo) VALUES" + "(null,'Drive to the airport','true')")
        db.execSQL("INSERT INTO $TABLE_TODO_JSON(${BaseColumns._ID},titleTodo,completedTodo) VALUES" + "(null,'Take a shower at night','false')")
        db.execSQL("INSERT INTO $TABLE_TODO_JSON(${BaseColumns._ID},titleTodo,completedTodo) VALUES" + "(null,'Walking with my dog','True')")
        Log.d("DB", "DATABASE CREATED SUCCESSFULLY")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate (db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}