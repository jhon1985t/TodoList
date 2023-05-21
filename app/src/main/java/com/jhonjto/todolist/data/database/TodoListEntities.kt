package com.jhonjto.todolist.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
@Parcelize
data class TodoListEntities(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val note: String,
    val date: LocalDateTime = LocalDateTime.now(),
    val isComplete: Boolean
): Parcelable {
    val createdDateFormatted : String
        get() = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"))
}
