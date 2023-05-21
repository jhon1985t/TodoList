package com.jhonjto.todolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jhonjto.todolist.ui.common.Converters

@Database(entities = [TodoListEntities::class], version = 1)
@TypeConverters(Converters::class)
abstract class TodoListDatabase: RoomDatabase() {

    abstract fun todoListDao(): TodoListDao
}
