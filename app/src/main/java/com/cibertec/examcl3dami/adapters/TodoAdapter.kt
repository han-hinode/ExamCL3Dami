package com.cibertec.examcl3dami.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.examcl3dami.R
import com.cibertec.examcl3dami.core.Todo

class TodoAdapter(private val data: MutableMap<Int, Todo>?) : RecyclerView.Adapter<TodoAdapter.TodoHolder>() {

    inner class TodoHolder(val v: View) : RecyclerView.ViewHolder(v) {
        var titleTodo: TextView
        var completedTodo: TextView

        init {
            titleTodo = v.findViewById(R.id.title_todo_list)
            completedTodo = v.findViewById(R.id.completed_todo_list)
        }

        fun bindData(data: Todo) = with(v) {
            titleTodo.text = "Title: ${data.titleTodo}"
            completedTodo.text = "Completed: ${data.completedTodo}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo_list, parent, false)
        return TodoHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data?.size?: 0
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        data?.let {
            it.get(position + 1)?.let { it1 -> holder.bindData(it1) }
        }
    }
}