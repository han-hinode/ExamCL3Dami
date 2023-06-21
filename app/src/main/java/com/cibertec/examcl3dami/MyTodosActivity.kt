package com.cibertec.examcl3dami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cibertec.examcl3dami.adapters.TodoAdapter
import com.cibertec.examcl3dami.core.Todo
import com.cibertec.examcl3dami.services.TodoService

class MyTodosActivity : AppCompatActivity() {

    var todoService = TodoService(this)
    lateinit var listTodosLocal: MutableMap<Int, Todo>
    lateinit var recycler: RecyclerView
    lateinit var exit: Button
    lateinit var pullToRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_todos)

        //BUTTON
        exit = findViewById(R.id.btnExit)
        listTodosLocal = todoService.getTodos()

        //REFRESH
        pullToRefresh = findViewById(R.id.swipeRefreshLayout)
        pullToRefresh.setOnRefreshListener {
            pullToRefresh.isRefreshing = false
            updateList()
        }

        //RECYCLER
        recycler = findViewById(R.id.my_todos_recycler_view)
        val adapter = TodoAdapter(listTodosLocal)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter

        exit.setOnClickListener{
            val intent = Intent(this, SearchJsonActitivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateList() {
        listTodosLocal = todoService.getTodos()
        val newAdapter = TodoAdapter(listTodosLocal)
        recycler.adapter = newAdapter
    }
}