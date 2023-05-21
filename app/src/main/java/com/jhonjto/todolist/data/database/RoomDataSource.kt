package com.jhonjto.todolist.data.database

import com.jhonjto.data.source.LocalDataSource
import com.jhonjto.domain.TodoList
import com.jhonjto.todolist.data.toDomainTodoList
import com.jhonjto.todolist.data.toRoomTodoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(
    db: TodoListDatabase
): LocalDataSource {
    private val todoListDao = db.todoListDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) {
            todoListDao.todoListCount() <= 0
        }

    override suspend fun saveTodoList(todoList: TodoList) =
        withContext(Dispatchers.IO) {
            todoListDao.insert(todoList.toRoomTodoList())
        }

    override suspend fun getAll(): List<TodoList> =
        withContext(Dispatchers.IO) {
            todoListDao.getAll().map { it.toDomainTodoList() }
        }

    override suspend fun findById(id: Int): TodoList =
        withContext(Dispatchers.IO) {
            todoListDao.findById(id).toDomainTodoList()
        }

    override suspend fun updateTodoList(todoList: TodoList) =
        withContext(Dispatchers.IO) {
            todoListDao.updateTodoList(todoList.toRoomTodoList())
        }

    override suspend fun deleteById(id: Int) =
        withContext(Dispatchers.IO) {
            todoListDao.deleteByUserId(id)
        }

    override suspend fun deleteAll() =
        withContext(Dispatchers.IO) {
            todoListDao.deleteAll()
        }
}
