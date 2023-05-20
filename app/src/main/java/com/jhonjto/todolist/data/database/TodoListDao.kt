package com.jhonjto.todolist.data.database

import androidx.room.*

@Dao
interface TodoListDao {

    @Query("SELECT * FROM TodoListEntities ORDER BY (favorite is true) DESC")
    fun getAll(): List<TodoListEntities>

    @Query("SELECT * FROM TodoListEntities WHERE id = :id")
    fun findById(id: Int): TodoListEntities

    @Query("SELECT COUNT(id) FROM TodoListEntities")
    fun todoListCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todoListEntities: TodoListEntities)

    @Update
    suspend fun updateTodoList(todoListEntities: TodoListEntities)

    @Query("DELETE FROM TodoListEntities WHERE id = :id")
    fun deleteByUserId(id: Int)

    @Delete
    fun deleteAll()
}