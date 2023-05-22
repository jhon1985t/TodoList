package com.jhonjto.usecases

import com.jhonjto.data.repositories.TodoListRepository

class DeleteById(
    private val todoListRepository: TodoListRepository
) {
    suspend fun invoke(id: Int) = todoListRepository.deleteById(id)
}
