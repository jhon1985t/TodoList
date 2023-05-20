package com.jhonjto.todolist.data.database

import com.jhonjto.data.source.LocalDataSource
import com.jhonjto.domain.TodoList
import com.jhonjto.todolist.data.toDomainTodoList
import com.jhonjto.todolist.data.toRoomTodoList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(
    db: TodoListDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
): LocalDataSource {
    private val todoListDao = db.todoListDao()

    override suspend fun isEmpty(): Boolean =
        withContext(dispatcher) {
            todoListDao.todoListCount() <= 0
        }

    override suspend fun saveTodoList(todoList: TodoList) =
        withContext(dispatcher) {
            todoListDao.insert(todoList.toRoomTodoList())
        }

    override suspend fun getAll(): List<TodoList> =
        withContext(dispatcher) {
            todoListDao.getAll().map { it.toDomainTodoList() }
        }

    override suspend fun findById(id: Int): TodoList =
        withContext(dispatcher) {
            todoListDao.findById(id).toDomainTodoList()
        }

    override suspend fun updateTodoList(todoList: TodoList) =
        withContext(dispatcher) {
            todoListDao.updateTodoList(todoList.toRoomTodoList())
        }

    override suspend fun deleteById(id: Int) =
        withContext(dispatcher) {
            todoListDao.deleteByUserId(id)
        }

    override suspend fun deleteAll() =
        withContext(dispatcher) {
            todoListDao.deleteAll()
        }
}
