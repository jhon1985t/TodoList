package com.jhonjto.todolist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class TodoListEntities(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val note: String,
    val date: LocalDateTime = LocalDateTime.now(),
    val isComplete: Boolean
)
