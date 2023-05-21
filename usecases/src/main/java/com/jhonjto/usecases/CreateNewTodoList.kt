package com.jhonjto.usecases

import com.jhonjto.data.repositories.TodoListRepository
import com.jhonjto.domain.TodoList

class CreateNewTodoList(
    private val todoListRepository: TodoListRepository
) {
    suspend fun invoke(todoList: TodoList) = todoListRepository.saveTodoList(todoList)
}
