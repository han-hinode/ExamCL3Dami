package com.cibertec.examcl3dami.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.cibertec.examcl3dami.config.DatabaseJson
import com.cibertec.examcl3dami.config.TABLE_TODO_JSON
import com.cibertec.examcl3dami.core.Todo

class TodoService(context: Context) {

   var dbHelper = DatabaseJson(context)

   fun insertNewTodo(titleTodo: String, completedTodo: String) {
      val db = dbHelper.writableDatabase
      val values = ContentValues().apply {
         put("titleTodo", titleTodo)
         put("completedTodo", completedTodo)
      }
      db?.insert(TABLE_TODO_JSON, null, values)
      cerrarDB()
   }

   fun getTodos(): MutableMap<Int, Todo> {
      val db = dbHelper.readableDatabase
      val projection = arrayOf(
         BaseColumns._ID,
         "titleTodo",
         "completedTodo"
      )
      val cursor = db.query(
         TABLE_TODO_JSON,
         projection,
         null,
         null,
         null,
         null,
         null
      )
      val todoObjs = mutableMapOf<Int, Todo>()
      with(cursor) {
         while (moveToNext()) {
            val index = getInt(getColumnIndexOrThrow(BaseColumns._ID))
            val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
            val title = getString(getColumnIndexOrThrow("titleTodo"))
            val completed = getString(getColumnIndexOrThrow("completedTodo"))

            val todoObj = Todo(
               id,
               title,
               completed
            )
            todoObjs.put(index, todoObj)
         }
      }
      cerrarDB()
      return todoObjs
   }

   fun cerrarDB(){
      dbHelper.close()
   }

}