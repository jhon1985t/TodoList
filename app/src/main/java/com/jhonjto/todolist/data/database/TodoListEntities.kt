package com.jhonjto.todolist.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Entity
@Parcelize
data class TodoListEntities(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String?,
    val note: String?,
    val date: LocalDateTime = LocalDateTime.now(),
    val isComplete: Boolean
): Parcelable
