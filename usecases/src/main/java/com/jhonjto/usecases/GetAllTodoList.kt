package com.jhonjto.usecases

import com.jhonjto.data.repositories.TodoListRepository
import com.jhonjto.domain.TodoList

class GetAllTodoList(private val todoListRepository: TodoListRepository) {
    suspend fun invoke(): List<TodoList> = todoListRepository.getTodoList()
}
