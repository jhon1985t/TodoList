package com.jhonjto.data.source

import com.jhonjto.domain.TodoList

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveTodoList(todoList: TodoList)
    suspend fun getAll(): List<TodoList>
    suspend fun findById(id: Int): TodoList
    suspend fun updateTodoList(todoList: TodoList)
    suspend fun deleteById(id: Int)
    suspend fun deleteAll()
}
