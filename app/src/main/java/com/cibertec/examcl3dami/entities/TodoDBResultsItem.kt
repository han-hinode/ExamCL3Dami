package com.cibertec.examcl3dami.entities

data class TodoDBResultsItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)