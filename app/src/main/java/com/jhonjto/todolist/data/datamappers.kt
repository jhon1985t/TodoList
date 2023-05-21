package com.jhonjto.todolist.data

import com.jhonjto.domain.TodoList
import com.jhonjto.todolist.data.database.TodoListEntities

fun TodoList.toRoomTodoList(): TodoListEntities = TodoListEntities(
    id,
    title,
    note,
    date,
    isComplete
)

fun TodoListEntities.toDomainTodoList(): TodoList = TodoList(
    id,
    title,
    note,
    date,
    isComplete
)
