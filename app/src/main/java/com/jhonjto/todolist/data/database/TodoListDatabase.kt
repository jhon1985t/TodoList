package com.jhonjto.todolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoListEntities::class], version = 1)
abstract class TodoListDatabase: RoomDatabase() {

    abstract fun todoListDao(): TodoListDao
}
