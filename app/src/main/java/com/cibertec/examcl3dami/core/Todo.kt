package com.cibertec.examcl3dami.core

class Todo {
    var idTodo: Long
    var titleTodo: String
    var completedTodo: String

    constructor(idTodo: Long, titleTodo: String, completedTodo: String) {
        this.idTodo = idTodo
        this.titleTodo = titleTodo
        this.completedTodo = completedTodo
    }
}