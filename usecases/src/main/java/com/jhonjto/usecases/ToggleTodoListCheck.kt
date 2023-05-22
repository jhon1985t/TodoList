package com.jhonjto.usecases

import com.jhonjto.data.repositories.TodoListRepository
import com.jhonjto.domain.TodoList

class ToggleTodoListCheck(
    private val todoListRepository: TodoListRepository
) {
    suspend fun invoke(todoList: TodoList): TodoList = with(todoList)  {
        copy(isComplete = !isComplete).also { todoListRepository.update(it) }
    }
}
