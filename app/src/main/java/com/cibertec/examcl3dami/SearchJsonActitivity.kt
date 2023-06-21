package com.cibertec.examcl3dami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cibertec.examcl3dami.entities.TodoDBResultsItem
import com.cibertec.examcl3dami.services.ResultService
import com.cibertec.examcl3dami.services.TodoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class SearchJsonActitivity : AppCompatActivity() {

    var todoService = TodoService(this)
    lateinit var titleTodo: EditText
    lateinit var completedTodo: EditText
    lateinit var createTodo: Button
    lateinit var listTodo: Button
    lateinit var retrofit: Retrofit
    lateinit var service: ResultService
    lateinit var idTodo: EditText
    lateinit var search: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_json_actitivity)

        // ID's
        titleTodo = findViewById(R.id.titleTodo)
        completedTodo = findViewById(R.id.completedTodo)
        createTodo = findViewById(R.id.createTodo)
        listTodo = findViewById(R.id.listTodo)
        idTodo = findViewById(R.id.idTodo)
        search = findViewById(R.id.search)

        // CREATE NEW REGISTER
        createTodo.setOnClickListener{
            if(titleTodo.text.isNotBlank() && completedTodo.text.isNotBlank()){
                todoService.insertNewTodo(titleTodo.text.toString(), completedTodo.text.toString())
                titleTodo.text.clear()
                completedTodo.text.clear()
                Toast.makeText(this, "REGISTERED SUCCESSFULLY", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "SORRY NOT REGISTERED", Toast.LENGTH_SHORT).show()
            }

        }

        // LIST ALL TODOS IN RECYCLER VIEW
        listTodo.setOnClickListener{
            val intent = Intent(this, MyTodosActivity::class.java)
            startActivity(intent)
        }

        search.setOnClickListener{

            getCourse()
        }

    }
    fun getCourse(){
        retrofit = Retrofit.Builder()
            .baseUrl(this.resources.getString(R.string.baseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ResultService::class.java)
        val call = service.getItemTodo(idTodo.text.toString())
        call.enqueue(object : Callback<TodoDBResultsItem>{
            override fun onResponse(call: Call<TodoDBResultsItem>, response: Response<TodoDBResultsItem>) {
                if(response.isSuccessful()){
                    val todo = response.body()!!
                    titleTodo.setText(todo.title)
                    completedTodo.setText(todo.completed.toString())
                }
            }

            override fun onFailure(call: Call<TodoDBResultsItem>, t: Throwable) {
                Toast.makeText(this@SearchJsonActitivity, "ERROR CONNECTION, TURN ON YOUR INTERNET AND TRY AGAIN ", Toast.LENGTH_LONG).show()
            }
        })

    }

}