package com.jhonjto.domain

import java.time.LocalDateTime

data class TodoList(
    val id: Int? = null,
    val title: String? = null,
    val note: String? = null,
    val date: LocalDateTime? = null,
    val isComplete: Boolean? = null
): java.io.Serializable
