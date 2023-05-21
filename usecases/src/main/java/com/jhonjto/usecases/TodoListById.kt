package com.jhonjto.usecases

import com.jhonjto.data.repositories.TodoListRepository
import com.jhonjto.domain.TodoList

class TodoListById(
    private val  todoListRepository: TodoListRepository
) {
    suspend fun invoke(id: Int): TodoList = todoListRepository.findById(id)
}
