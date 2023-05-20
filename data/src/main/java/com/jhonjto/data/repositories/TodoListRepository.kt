package com.jhonjto.data.repositories

import com.jhonjto.data.source.LocalDataSource
import com.jhonjto.domain.TodoList

class TodoListRepository(
    private val localDataSource: LocalDataSource
) {

    suspend fun databaseIsEmpty(): Boolean {
        return localDataSource.isEmpty()
    }

    suspend fun saveTodoList(todoList: TodoList) {
        return localDataSource.saveTodoList(todoList)
    }

    suspend fun getTodoList(): List<TodoList> {
        return localDataSource.getAll()
    }

    suspend fun findById(id: Int): TodoList = localDataSource.findById(id)

    suspend fun update(todoList: TodoList) = localDataSource.updateTodoList(todoList)

    suspend fun deleteById(id: Int) = localDataSource.deleteById(id)

    suspend fun deleteAll() = localDataSource.deleteAll()
}