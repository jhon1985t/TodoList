package com.jhonjto.domain

data class TodoList(
    val id: Int,
    val title: String,
    val note: String,
    val date: String,
    val isComplete: Boolean
)
