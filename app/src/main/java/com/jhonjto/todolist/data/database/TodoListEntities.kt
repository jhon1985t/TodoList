package com.jhonjto.todolist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoListEntities(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val note: String,
    val date: String,
    val isComplete: Boolean
)
