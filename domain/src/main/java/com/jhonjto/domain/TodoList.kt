package com.jhonjto.domain

import java.time.LocalDateTime

data class TodoList(
    val id: Int?,
    val title: String?,
    val note: String?,
    val date: LocalDateTime,
    val isComplete: Boolean
): java.io.Serializable
