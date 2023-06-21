package com.cibertec.examcl3dami.services

import com.cibertec.examcl3dami.entities.TodoDBResultsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ResultService {
    @GET("todos/{id}")
    fun getItemTodo(@Path("id") id: String) : Call<TodoDBResultsItem>
}